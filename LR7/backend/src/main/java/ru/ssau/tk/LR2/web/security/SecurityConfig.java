package ru.ssau.tk.LR2.web.security;

import jakarta.servlet.ServletOutputStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import ru.ssau.tk.LR2.jpa.model.User;
import ru.ssau.tk.LR2.jpa.repository.UserRepository;
import ru.ssau.tk.LR2.web.service.UserService;

import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123");
        http
                .csrf((csrf) -> csrf.disable())
                .cors((cors) -> cors.disable())
                .exceptionHandling((handle) -> handle.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/users/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .successHandler((request, response, authentication) -> {
                            ServletOutputStream out = response.getOutputStream();
                            out.print("logged in as " + authentication.getName());
                            out.flush();
                        })
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .addLogoutHandler((request, response, authentication) -> {
                            try {
                                ServletOutputStream out = response.getOutputStream();
                                out.print("logout");
                                out.flush();
                            } catch (IOException ignored){

                            }
                        })
                        .permitAll());


        return http.build();
    }

    @Bean
    public UserDetailsManager userDetailsManager(PasswordEncoder encoder, UserRepository repo) {
        UserDetailsManager service = new UserService(repo);

        if(!service.userExists("user")) {
            service.createUser(new User(
                    "user",
                    encoder.encode("password")
            ));
        }

        return service;
    }
}