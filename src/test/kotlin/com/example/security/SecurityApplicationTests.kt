package com.example.security

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecurityApplicationTests {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun testGetRequest() {
        webTestClient.get()
            .uri("/sample")
            .exchange()
            .expectStatus().isForbidden
    }

    @Test
    fun testGetRequest2() {
        webTestClient.get()
            .uri("/sample2")
            .exchange()
            .expectStatus().isForbidden

    }

    @Test
    fun testGetRequest3() {
        webTestClient.get()
            .uri("/sample3")
            .exchange()
            .expectStatus().isOk
    }

}
