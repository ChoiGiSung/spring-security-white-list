package com.example.security

import com.example.security.authorizationmanager.CustomPreAuthorizationManager
import com.example.security.authorizationmanager.CustomSecuredAuthorizationManager
import com.example.security.authorizationmanager.HttpAuthTokenAuthorizationManager
import com.example.security.authorizationmanager.HttpAuthTokenWebAuthorizationManager
import org.aopalliance.intercept.MethodInterceptor
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Role
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Configuration
    @EnableWebSecurity
    internal class WebSecurityConfig(
        private val httpAuthTokenWebAuthorizationManager: HttpAuthTokenWebAuthorizationManager,
    ) {

        @Bean
        fun filterChain(http: HttpSecurity): SecurityFilterChain {
            return http
                .csrf { it.disable() }
                .authorizeHttpRequests {
                    it.anyRequest().authenticated()
//                    it.anyRequest().access(httpAuthTokenWebAuthorizationManager)
                }
                .build()
        }

    }

    @Configuration
    @EnableMethodSecurity(
        // Custom security implementation is used, so other security features are disabled
        prePostEnabled = false,
        securedEnabled = false,
    )
    internal class MethodSecurityConfig(
        private val httpAuthTokenAuthorizationManager: HttpAuthTokenAuthorizationManager,
    ) {

        @Bean
        @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
        fun preAuthorizationMethodInterceptor(): MethodInterceptor {
            return AuthorizationManagerBeforeMethodInterceptor.preAuthorize(
                CustomPreAuthorizationManager(
                    httpAuthTokenAuthorizationManager
                )
            )
        }

        @Bean
        @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
        fun securedAuthorizationMethodInterceptor(): MethodInterceptor {
            return AuthorizationManagerBeforeMethodInterceptor.secured(
                CustomSecuredAuthorizationManager(
                    httpAuthTokenAuthorizationManager
                )
            )
        }

    }
}