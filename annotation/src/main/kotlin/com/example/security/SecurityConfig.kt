package com.example.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.AccessDecisionManager
import org.springframework.security.access.AccessDecisionVoter
import org.springframework.security.access.expression.method.ExpressionBasedPreInvocationAdvice
import org.springframework.security.access.prepost.PreInvocationAuthorizationAdviceVoter
import org.springframework.security.access.vote.AffirmativeBased
import org.springframework.security.access.vote.AuthenticatedVoter
import org.springframework.security.access.vote.RoleVoter
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.expression.WebExpressionVoter


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // 메소드보호 호출
@EnableWebSecurity
class SecurityConfig(
    private val whiteListVoter: HttpWhiteListVoter
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeRequests()
            .accessDecisionManager(webAccessDecisionManager())
            .anyRequest().authenticated()
            .and().build();
    }

    @Bean
    fun webAccessDecisionManager(): AccessDecisionManager {
        val decisionVoters: MutableList<AccessDecisionVoter<*>> = ArrayList()
        decisionVoters.add(WebExpressionVoter())
        decisionVoters.add(whiteListVoter)
        return AffirmativeBased(decisionVoters)
    }

}
