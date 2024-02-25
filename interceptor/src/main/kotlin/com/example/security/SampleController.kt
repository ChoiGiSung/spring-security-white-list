package com.example.security

import com.example.security.authorizationmanager.HttpAuthToken
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Secured("ROLE_ADMIN")
class SampleController(
) {

    @GetMapping("/sample")
    fun sample(): String {
        return "sample"
    }

    @HttpAuthToken
    @GetMapping("/sample2")
    fun sample2(): String {
        return "sample2"
    }

}