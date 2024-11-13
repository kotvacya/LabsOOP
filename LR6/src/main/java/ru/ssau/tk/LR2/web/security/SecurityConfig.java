package ru.ssau.tk.LR2.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.ssau.tk.LR2.jdbc.repository.UserRepository;
import ru.ssau.tk.LR2.web.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .cors((cors) -> cors.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/users/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .successHandler((request, response, authentication) -> {})
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .addLogoutHandler((request, response, authentication) -> {})
                        .permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsManager userDetailsManager(PasswordEncoder encoder, UserRepository repo) {
        return new UserService(repo);
    }
}