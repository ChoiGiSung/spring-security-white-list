package com.example.security.authorizationmanager

import org.aopalliance.intercept.MethodInvocation
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.authorization.method.PreAuthorizeAuthorizationManager
import org.springframework.security.core.Authentication
import java.util.function.Supplier

class CustomPreAuthorizationManager(
    private val httpAuthTokenAuthorizationManager: HttpAuthTokenAuthorizationManager
) : AuthorizationManager<MethodInvocation> {
    private val preAuthorizeAuthorizationManager = PreAuthorizeAuthorizationManager()


    override fun check(authentication: Supplier<Authentication>, `object`: MethodInvocation): AuthorizationDecision {
        val whiteListDecision = httpAuthTokenAuthorizationManager.check(authentication, `object`)
        val securedDecision = preAuthorizeAuthorizationManager.check(authentication, `object`)!!
        return AuthorizationDecision(whiteListDecision.isGranted || securedDecision.isGranted)
    }
}