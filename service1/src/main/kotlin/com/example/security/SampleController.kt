package com.example.security

import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController {

    @GetMapping("/sample")
    fun sample(): String {
        return "sample"
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/sample2")
    fun sample2(): String {
        return "sample2"
    }
}