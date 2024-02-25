package com.example.security.authorizationmanager

import com.example.security.authorizationmanager.HttpAuthTokenValidator.Companion.HTTP_AUTH_TOKEN
import jakarta.servlet.http.HttpServletRequest
import org.aopalliance.intercept.MethodInvocation
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.function.Supplier

@Component
class HttpAuthTokenAuthorizationManager(
    private val httpAuthTokenValidator: HttpAuthTokenValidator
) : AuthorizationManager<MethodInvocation> {

    override fun check(authentication: Supplier<Authentication>, `object`: MethodInvocation): AuthorizationDecision {
        `object`.method.getAnnotation(HttpAuthToken::class.java) ?: return AuthorizationDecision(false)
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        return AuthorizationDecision(getIpAddressAllowed(request))
    }

    private fun getIpAddressAllowed(request: HttpServletRequest): Boolean {
        val provideToken = request.getHeader(HTTP_AUTH_TOKEN) ?: return false
        return httpAuthTokenValidator.validateToken(provideToken)
    }
}