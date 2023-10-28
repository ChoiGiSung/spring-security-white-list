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
        val allowedRoles = getAllowedRoles(request)

        val authorities = SecurityContextHolder.getContext().authentication?.authorities ?: emptyList()
        val isAuthenticated = authorities.any { it.authority in allowedRoles }

        // 현재 요청의 IP 주소를 가져오기 위해 RequestContextHolder 사용
        val ip = request.remoteAddr
        val ipSplits = ips?.split(",") ?: emptyList()
        val isIpAddressAllowed = ip in ipSplits

        return isAuthenticated || isIpAddressAllowed
    }

    fun getAllowedRoles(httpServletRequest: HttpServletRequest): Array<String> {
        val attribute = httpServletRequest.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE) as HandlerMethod
        val methodAnnotation = attribute.getMethodAnnotation(SecuredOrLocalhost::class.java)
        return methodAnnotation?.roles ?: emptyArray()
    }
}