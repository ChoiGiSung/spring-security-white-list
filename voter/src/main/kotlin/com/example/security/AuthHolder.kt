package com.example.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerMapping
import javax.servlet.http.HttpServletRequest


@Component
class AuthHolder {

    @Value("\${my.allowed.ip}")
    private val ips: String? = null

    fun getSomeRole(): Boolean {
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        return  getIpAddressAllowed(request)
    }

    private fun getIpAddressAllowed(request: HttpServletRequest): Boolean {
        val ip = request.remoteAddr
        val ipSplits = ips?.split(",") ?: emptyList()
        return ip in ipSplits
    }


}