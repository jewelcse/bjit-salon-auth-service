package com.bjit.salon.auth.service.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Ignore
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc

class UserControllerIntegrationTest extends Specification {

    @Autowired
    private MockMvc mockMvc;

    def "should create a new user account"(){

        given:
        String content = "{\n" +
                "    \"firstName\":\"Abul Kalam\",\n" +
                "    \"lastName\":\"Hossain\",\n" +
                "    \"username\":\"abulkalam\",\n" +
                "    \"email\":\"abulkalam@gmail.com\",\n" +
                "    \"password\":\"abulkalam\",\n" +
                "    \"role\":\"ROLE_ADMIN\"\n" +
                "}"


        expect:
        mockMvc.perform(post("/api/v1/sign-up")
                .content(content).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().is2xxSuccessful())

    }

    def "should login user account by providing the user details"(){
        given:
        String content = "{\n" +
                "    \"username\":\"abulkalam\",\n" +
                "    \"password\":\"abulkalam\"\n" +
                "}"


        expect:
        mockMvc.perform(post("/api/v1/sign-in")
                .content(content).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().is2xxSuccessful())
    }

    def "should activated user account"(){
        expect:
        mockMvc.perform(get("/api/v1/users/actions/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    def "should de-activated user account"(){
        expect:
        mockMvc.perform(get("/api/v1/users/actions/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

}