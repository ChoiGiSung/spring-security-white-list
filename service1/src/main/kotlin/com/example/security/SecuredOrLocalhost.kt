package com.example.security

import org.springframework.security.access.prepost.PreAuthorize

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("hasRole('ROLE_ADMIN') or #request.getRemoteAddr().equals('127.0.0.1')")
annotation class SecuredOrLocalhost