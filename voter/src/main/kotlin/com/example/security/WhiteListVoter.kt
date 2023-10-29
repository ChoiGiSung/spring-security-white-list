package com.example.security

import org.springframework.security.access.AccessDecisionVoter
import org.springframework.security.access.AccessDecisionVoter.ACCESS_DENIED
import org.springframework.security.access.AccessDecisionVoter.ACCESS_GRANTED
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class WhiteListVoter(
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
        if (authHolder.getSomeRole()) {
            return ACCESS_GRANTED
        }
        return ACCESS_DENIED
    }

}