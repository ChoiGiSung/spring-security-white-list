package com.example.security

import org.springframework.security.access.AccessDecisionVoter
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Component
class HttpWhiteListVoter(
    private val authHolder: AuthHolder
) : AccessDecisionVoter<Any> {

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
        if (authHolder.getIpAddressAllowed(request)) {
            return AccessDecisionVoter.ACCESS_GRANTED
        }
        return AccessDecisionVoter.ACCESS_DENIED
    }

}
