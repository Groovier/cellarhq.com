package com.cellarhq.auth

import com.cellarhq.ratpack.hibernate.HibernateDSL
import com.cellarhq.services.AccountService
import com.google.inject.Inject
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.hibernate.SessionFactory
import org.pac4j.core.exception.CredentialsException
import org.pac4j.http.credentials.UsernamePasswordAuthenticator
import org.pac4j.http.credentials.UsernamePasswordCredentials

@Slf4j
@CompileStatic
class CellarHQUsernamePasswordAuthenticator implements UsernamePasswordAuthenticator {

    private final AccountService accountService
    private final SessionFactory sessionFactory

    @Inject
    CellarHQUsernamePasswordAuthenticator(AccountService accountService, SessionFactory sessionFactory) {
        this.accountService = accountService
        this.sessionFactory = sessionFactory
    }

    @Override
    void validate(UsernamePasswordCredentials credentials) {
        if (credentials == null) {
            throwsException('No credentials')
        }

        HibernateDSL.transaction(sessionFactory) {
            if (!accountService.findByCredentials(credentials.username, credentials.password)) {
                throwsException('Email and/or password did not match')
            }
        }
    }

    protected void throwsException(final String message) {
        throw new CredentialsException(message)
    }
}
