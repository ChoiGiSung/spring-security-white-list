package com.example.security

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
class SecurityApplicationTests {

    @Autowired
    lateinit var mockMvc : MockMvc

    @Autowired
    lateinit var authHolder: AuthHolder

    @Test
    @WithMockUser(roles = ["ADMIN"])
    fun sample1WithRole() {
        mockMvc.perform(get("/sample"))
            .andExpect(status().isOk)
    }

    @Test
    fun sample1() {
        mockMvc.perform(get("/sample"))
            .andExpect(status().isOk)
    }

    @Test
    fun sample2() {
        mockMvc.perform(get("/sample2"))
            .andExpect(status().isOk)
    }

    @Test
    @WithMockUser(roles = ["ADMIN"])
    fun sample2WithRole() {
        mockMvc.perform(get("/sample2"))
            .andExpect(status().isOk)
    }

}
