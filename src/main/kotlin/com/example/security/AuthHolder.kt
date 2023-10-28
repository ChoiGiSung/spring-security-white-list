package com.example.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import javax.servlet.http.HttpServletRequest


@Component
class AuthHolder {

    @Value("\${my.allowed.ip}")
    private val ips: String? = null

    fun getSomeRole(): Boolean {
        val authorities = SecurityContextHolder.getContext().authentication?.authorities ?: emptyList()
        for (auth in authorities) {
            if (auth.authority.equals("ROLE_ADMIN")) {
                return true
            }
        }

        // 현재 요청의 IP 주소를 가져오기 위해 RequestContextHolder 사용
        val requestAttributes = RequestContextHolder.currentRequestAttributes()
        val ipSplits = this.ips?.split(",") ?: emptyList()

        if (requestAttributes is ServletRequestAttributes) {
            val ip = requestAttributes.request.remoteAddr
            for (allowedIp in ipSplits) {
                if (ip == allowedIp) {
                    return true
                }
            }
        }

        return false

    }
}