package com.cellarhq.endpoints.api

import com.cellarhq.Messages
import com.cellarhq.auth.SecurityModule
import com.cellarhq.domain.CellaredDrink
import com.cellarhq.jooq.SortCommand
import com.cellarhq.services.CellaredDrinkService
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain
import ratpack.handling.Context
import ratpack.session.store.SessionStorage

import static ratpack.jackson.Jackson.fromJson
import static ratpack.jackson.Jackson.json

@Slf4j
class CellaredDrinkEndpoint implements Action<Chain> {

    CellaredDrinkService cellaredDrinkService

    @Inject
    CellaredDrinkEndpoint(CellaredDrinkService cellaredDrinkService) {
        this.cellaredDrinkService = cellaredDrinkService
    }

    @Override
    void execute(Chain chain) throws Exception {
        Groovy.chain(chain) {

            get('cellars/:cellarSlug/drinks') {
                cellaredDrinkService.all(
                        pathTokens['cellarSlug'], SortCommand.fromRequest(request)).toList().subscribe { List<CellaredDrink> drinks ->
                    render json(drinks)
                }
            }

            put('cellars/:cellarSlug/drinks/:id/drink') {
                String slug = pathTokens['cellarSlug']
                Long id = Long.valueOf(pathTokens['id'])

                cellaredDrinkService.findById(id).single().subscribe { CellaredDrink drink ->
                    requireSelf(context, drink) {
                        cellaredDrinkService.drink(slug, id).single().subscribe { CellaredDrink drankDrink ->
                            if (drankDrink == null) {
                                clientError 404
                            } else {
                                render json(drankDrink)
                            }
                        }
                    }
                }
            }

            handler('cellars/:cellarSlug/drinks/:id') {
                byMethod {
                    get {
                        Long id = Long.valueOf(pathTokens['id'])

                        cellaredDrinkService.findById(id).single().subscribe { CellaredDrink drink ->
                            requireSelf(context, drink) {
                                if (drink) {
                                    render json(drink)
                                } else {
                                    clientError 404
                                }
                            }
                        }
                    }

                    post {
                        CellaredDrink cellaredDrink = parse(fromJson(CellaredDrink))
                        requireSelf(context, cellaredDrink) {
                            cellaredDrinkService.save(cellaredDrink)
                                    .single().flatMap {
                                cellaredDrinkService.findById(it.id).single()
                            } subscribe { CellaredDrink createdDrink ->
                                render json(createdDrink)
                            }
                        }
                    }

                    delete {
                        Long id = Long.valueOf(pathTokens['id'])

                        cellaredDrinkService.findById(id).single().subscribe { CellaredDrink drink ->
                            requireSelf(context, drink) {
                                cellaredDrinkService.delete(id).subscribe {
                                    response.status(204).send()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    void requireSelf(Context context, CellaredDrink cellaredDrink, Closure operation) {
        context.with {
            Long cellarId = (Long) request.get(SessionStorage).get(SecurityModule.SESSION_CELLAR_ID)
            boolean isSelf = cellaredDrink.cellarId == cellarId

            if (isSelf) {
                operation()
            } else {
                response.status(403)
                render json([
                        message: Messages.UNAUTHORIZED_ERROR
                ])
                response.send()
            }
        }
    }

}
