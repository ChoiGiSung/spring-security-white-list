package com.example.security.authorizationmanager

import org.aopalliance.intercept.MethodInvocation
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.authorization.method.SecuredAuthorizationManager
import org.springframework.security.core.Authentication
import java.util.function.Supplier

class CustomSecuredAuthorizationManager(
    private val httpAuthTokenAuthorizationManager: HttpAuthTokenAuthorizationManager
) : AuthorizationManager<MethodInvocation> {
    private val securedAuthorizationManager = SecuredAuthorizationManager()


    override fun check(authentication: Supplier<Authentication>, `object`: MethodInvocation): AuthorizationDecision {
        val httpAuthTokenDecision = httpAuthTokenAuthorizationManager.check(authentication, `object`)
        val securedDecision = securedAuthorizationManager.check(authentication, `object`)!!
        return AuthorizationDecision(httpAuthTokenDecision.isGranted || securedDecision.isGranted)
    }
}