package com.example.security.authorizationmanager

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component


@Component
class HttpAuthTokenValidator(
    @Value("\${http.auth.token}")
    private val token: String
) {

    companion object {
        public const val HTTP_AUTH_TOKEN: String = "HTTP_AUTH_TOKEN"
    }

    fun validateToken(provideToken: String): Boolean {
        //todo something encryption check
        return provideToken == token
    }
}