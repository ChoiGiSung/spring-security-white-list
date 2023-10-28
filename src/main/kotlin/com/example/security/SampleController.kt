package com.example.security

import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.security.core.parameters.P
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@Secured("ROLE_ADMIN")
class SampleController(
    private val authHolder: AuthHolder
) {

    @GetMapping("/sample")
    fun sample(): String {
        return "sample"
    }

    @GetMapping("/sample2")
    fun sample2(): String {
        return "sample2"
    }

    @SecuredOrLocalhost
    @GetMapping("/sample3")
    fun sample3(request: HttpServletRequest): String {
        return "sample3"
    }

}