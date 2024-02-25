package com.example.security.authorizationmanager

import com.example.security.authorizationmanager.HttpAuthTokenValidator.Companion.HTTP_AUTH_TOKEN
import jakarta.servlet.http.HttpServletRequest
import org.aopalliance.intercept.MethodInvocation
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.access.intercept.RequestAuthorizationContext
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.function.Supplier

@Component
class HttpAuthTokenWebAuthorizationManager(
    private val httpAuthTokenValidator: HttpAuthTokenValidator
) : AuthorizationManager<RequestAuthorizationContext> {

    override fun check(
        authentication: Supplier<Authentication>?,
        `object`: RequestAuthorizationContext?
    ): AuthorizationDecision? {
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        return AuthorizationDecision(getAllowed(request))
    }

    private fun getAllowed(request: HttpServletRequest): Boolean {
        val provideToken = request.getHeader(HTTP_AUTH_TOKEN) ?: return false
        return httpAuthTokenValidator.validateToken(provideToken)
    }


}