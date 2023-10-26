package com.example.security

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status



@WebMvcTest
class SecurityApplicationTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun testGetRequest() {
        mockMvc.perform(get("/sample"))
            .andDo(print())
            .andExpect(status().isForbidden)

    }

}
