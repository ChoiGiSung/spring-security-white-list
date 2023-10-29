package com.example.security

import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Secured("ROLE_ADMIN")
class SampleController(
    private val authHolder: AuthHolder
) {

    @GetMapping("/sample")
    fun sample(): String {
        return "sample"
    }

    @SecuredOrLocalhost
    @GetMapping("/sample2")
    fun sample2(): String {
        return "sample2"
    }

}