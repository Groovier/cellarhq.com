package com.cellarhq.webapp

import com.cellarhq.api.services.DrinkService
import com.cellarhq.api.services.OrganizationService
import com.cellarhq.auth.services.photo.PhotoService
import com.cellarhq.common.Messages
import com.cellarhq.common.session.FlashMessage
import com.cellarhq.common.validation.ValidationErrorMapper
import com.cellarhq.domain.Organization
import com.cellarhq.domain.OrganizationType
import com.cellarhq.domain.Photo
import com.cellarhq.jooq.SortCommand
import com.cellarhq.util.LogUtil
import com.cellarhq.util.SessionUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.form.Form
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain

import javax.validation.ConstraintViolation
import javax.validation.Validator
import javax.validation.ValidatorFactory

import static ratpack.handlebars.Template.handlebarsTemplate

@SuppressWarnings(['MethodSize'])
@Slf4j
class BreweryEndpoint implements Action<Chain> {
  ValidatorFactory validatorFactory
  OrganizationService organizationService
  DrinkService drinkService
  PhotoService photoService

  @Inject
  public BreweryEndpoint(ValidatorFactory validatorFactory,
                         OrganizationService organizationService,
                         DrinkService drinkService,
                         PhotoService photoService) {

    this.validatorFactory = validatorFactory
    this.organizationService = organizationService
    this.drinkService = drinkService
    this.photoService = photoService
  }

  @Override
  void execute(Chain chain) throws Exception {
    Groovy.chain(chain) {
      path('breweries') {
        byMethod {
          /**
           * List all breweries; has search.
           */
          get {
            Integer requestedPage = request.queryParams.page?.toInteger() ?: 1
            Integer pageSize = 20
            Integer offset = (requestedPage - 1) * pageSize
            String searchTerm = request.queryParams.search

            rx.Observable<Integer> totalCount = searchTerm ?
              organizationService.searchCount(searchTerm).single() :
              organizationService.count().single()

            rx.Observable organizations = searchTerm ?
              organizationService.search(
                searchTerm, SortCommand.fromRequest(request), pageSize, offset).toList() :
              organizationService.all(SortCommand.fromRequest(request), pageSize, offset).toList()

            rx.Observable.zip(organizations, totalCount) { List list, Integer count ->
              [
                organizations: list,
                totalCount   : count
              ]
            }.subscribe({ Map map ->
              Integer pageCount = (map.totalCount / pageSize)
              Boolean shouldShowPagination = pageCount != 0

              render handlebarsTemplate('breweries/list-brewery.html',
                [organizations       : map.organizations,
                 currentPage         : requestedPage,
                 totalPageCount      : pageCount,
                 shouldShowPagination: shouldShowPagination,
                 title               : 'CellarHQ : Breweries',
                 pageId              : 'breweries.list'])
            }, { Throwable t ->
              log.error(LogUtil.toLog(request, 'ListBreweriesError'), t)
              clientError 500
            })
          }

          /**
           * Add a new brewery.
           */
          post {
            parse(Form).then { Form form ->

              Organization organization = updateOrganizationFromForm(new Organization(), form)

              Validator validator = validatorFactory.validator
              Set<ConstraintViolation<Organization>> organizationViolations =
                validator.validate(organization)

              if (organizationViolations.empty) {
                organizationService.save(organization)
                  .single()
                  .subscribe({ Organization savedOrganization ->
                  redirect("/breweries/${savedOrganization.slug}")
                })
              } else {
                List<String> messages = new ValidationErrorMapper().buildMessages(organizationViolations)

                SessionUtil.setFlash(context, FlashMessage.error(Messages.FORM_VALIDATION_ERROR, messages)).then {
                  redirect('/breweries/add')
                }
              }
            }
          }
        }
      }

      /**
       * HTML page for adding a new brewery.
       */
      get('breweries/add') {
        render handlebarsTemplate('breweries/new-brewery.html',
          [organization: new Organization(),
           title       : 'CellarHQ : Add New Brewery',
           pageId      : 'breweries.new'])

      }

      /**
       * HTML page for editing breweries.
       */
      get('breweries/:slug/edit') {
        String slug = pathTokens['slug']
        organizationService.findBySlug(slug).single().subscribe { Organization organization ->
          if (organization) {
            if (organization.editable) {
              render handlebarsTemplate('breweries/edit-brewery.html',
                [organization: organization,
                 title       : "CellarHQ : Edit ${organization.name}",
                 pageId      : 'breweries.edit'])
            } else {
              clientError 403
            }
          } else {
            clientError 404
          }
        }
      }

      path('breweries/:slug') {
        byMethod {
          /**
           * Get an existing brewery.
           */
          get {
            String slug = pathTokens['slug']

            rx.Observable organizationObservable = organizationService.findBySlug(slug).single()
            rx.Observable drinksObservable = drinkService.findByOrganizationSlug(slug).toList()
            rx.Observable<Photo> photoObservable = photoService.findByOrganization(slug)

            rx.Observable.zip(organizationObservable, drinksObservable, photoObservable) {
              Organization org,
              List drinks,
              Photo photo ->
                [
                  organization: org,
                  drinks      : drinks,
                  photo       : photo
                ]
            }.subscribe({ Map map ->
              if (map.organization) {
                render handlebarsTemplate('breweries/show-brewery.html',
                  [
                    organization  : map.organization,
                    title         : "CellarHQ : ${map.organization.name}",
                    drinks        : map.drinks,
                    numberOfDrinks: map.drinks?.size() ?: 0,
                    photo         : map.photo,
                    pageId        : 'breweries.show'
                  ])
              } else {
                clientError 404
              }

            })
          }

          /**
           * Update an existing brewery
           */
          post {
            String slug = pathTokens['slug']
            parse(Form).then { Form form ->

              organizationService.findBySlug(slug).single().subscribe { Organization foundOrganization ->
                if (foundOrganization) {
                  if (foundOrganization.editable) {
                    updateOrganizationFromForm(foundOrganization, form)

                    organizationService.save(foundOrganization)
                      .single()
                      .subscribe { Organization savedOrganization ->
                      redirect("/breweries/${savedOrganization.slug}")
                    }
                  } else {
                    clientError 403
                  }
                } else {
                  clientError 404
                }
              }
            }
          }
        }
      }
    }
  }

  private Organization updateOrganizationFromForm(Organization organization, Form form) {
    organization.with {
      slug = organization.slug ?: form.name
      name = organization.name ?: form.name
      collaboration = form.collaboration ?: false
      description = form.description
      established = form.established ? Short.parseShort(form.established) : null
      phone = form.phone
      website = form.website
      address = form.address
      address2 = form.address2
      locality = form.city
      postalCode = form.postalCode
      country = form.country
      locked = false
      needsModeration = false
      warningFlag = false
      totalBeers = 0
      cellaredBeers = 0
      containedInCellars = 0
      type = OrganizationType.BREWERY
    }


    return organization
  }
}
