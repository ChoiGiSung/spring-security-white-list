package com.example.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest


@Component
class AuthHolder {

    @Value("\${my.allowed.ip}")
    private val ip: String? = null

    fun getSomeRole(request: HttpServletRequest): Boolean {
        return request.remoteAddr.equals(ip)
    }
}