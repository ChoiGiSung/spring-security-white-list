package com.example.security

import com.example.security.authorizationmanager.HttpAuthTokenValidator
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
class SecurityApplicationTests {

    @Value("\${http.auth.token}")
    private val token: String? = null

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    @WithMockUser(roles = ["ADMIN"])
    fun sample1WithRole() {
        mockMvc.perform(get("/sample"))
            .andExpect(status().isOk)
    }

    @Test
    fun sample1() {
        mockMvc.perform(get("/sample"))
            .andExpect(status().isForbidden)
    }

    @Test
    fun failedSample2() {
        mockMvc.perform(get("/sample2"))
            .andExpect(status().isForbidden)
    }

    @Test
    @WithMockUser(roles = ["ADMIN"])
    fun sample2WithRole() {
        mockMvc.perform(get("/sample2"))
            .andExpect(status().isOk)
    }

    @Test
    fun sample2WithToken() {
        mockMvc.perform(
            get("/sample2")
                .header(HttpAuthTokenValidator.HTTP_AUTH_TOKEN, token)
        ).andExpect(status().isOk)
    }

}