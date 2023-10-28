package com.example.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest


@Component
class AuthHolder {

    @Value("\${my.allowed.ip}")
    private val ips: String? = null

    fun getSomeRole(request: HttpServletRequest): Boolean {
        val authorities = SecurityContextHolder.getContext().authentication?.authorities ?: emptyList()
        for (auth in authorities) {
            if (auth.authority.equals("ROLE_ADMIN")) {
                return true
            }
        }

        val ips = this.ips?.split(",") ?: emptyList()
        for (ip in ips) {
            if (request.remoteAddr.equals(ip)) {
                return true
            }
        }
        return false
    }
}