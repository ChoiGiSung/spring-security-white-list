package com.example.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.access.AccessDecisionVoter
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import javax.servlet.http.HttpServletRequest

@Component
class HttpWhiteListVoter : AccessDecisionVoter<Any> {

    @Value("\${my.allowed.ip}")
    private val ips: String? = null


    override fun supports(attribute: ConfigAttribute?): Boolean {
        return true
    }

    override fun supports(clazz: Class<*>?): Boolean {
        return true
    }

    override fun vote(
        authentication: Authentication?,
        `object`: Any?,
        attributes: MutableCollection<ConfigAttribute>?
    ): Int {
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        if (this.getIpAddressAllowed(request)) {
            return AccessDecisionVoter.ACCESS_GRANTED
        }
        return AccessDecisionVoter.ACCESS_DENIED
    }

    private fun getIpAddressAllowed(request: HttpServletRequest): Boolean {
        val ip = request.remoteAddr
        val ipSplits = ips?.split(",") ?: emptyList()
        return ip in ipSplits
    }


}
