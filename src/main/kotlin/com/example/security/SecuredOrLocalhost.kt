package com.example.security

import org.springframework.security.access.prepost.PreAuthorize

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("@authHolder.getSomeRole(#request)")
annotation class SecuredOrLocalhost(
    val roles: Array<String> = ["ROLE_ADMIN"]
)