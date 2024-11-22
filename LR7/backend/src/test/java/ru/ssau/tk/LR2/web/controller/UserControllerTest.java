package ru.ssau.tk.LR2.web.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import ru.ssau.tk.LR2.jpa.model.User;
import ru.ssau.tk.LR2.jpa.repository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    PasswordEncoder encoder;

    @MockBean
    UserRepository userRepo;

    MockHttpSession session;

    @Test
    void testUserController() throws Exception {
        Mockito.when(userRepo.findByUsername("user1")).thenReturn(getUsers().get(0));
        Mockito.when(userRepo.findAll()).thenReturn(getUsers());
        Mockito.when(userRepo.findById(1)).thenReturn(getUsers().get(1));

        session = (MockHttpSession)mvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "user1")
                        .param("password", "pass1"))
                .andExpect(status().isOk())
                .andReturn().getRequest().getSession();

        mvc.perform(get("/users/whoami").session(session))
                .andExpect(status().isOk())
                .andExpect(content().string("user1"));

        mvc.perform(get("/users/").session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        mvc.perform(get("/users/1").session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("user2"));



        mvc.perform(get("/logout").session(session)).andExpect(status().isOk());
    }

    private List<User> getUsers() {
        return Arrays.asList(
                new User("user1", encoder.encode("pass1")),
                new User("user2", encoder.encode("pass2"))
        );

    }
}