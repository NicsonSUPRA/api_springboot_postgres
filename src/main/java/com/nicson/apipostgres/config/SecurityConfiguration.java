package com.nicson.apipostgres.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nicson.apipostgres.security.CustomUserDetailService;
import com.nicson.apipostgres.services.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
                return httpSecurity
                                .csrf(AbstractHttpConfigurer::disable)
                                .httpBasic(Customizer.withDefaults())
                                // .formLogin(configurer -> {
                                // configurer.loginPage("/login").permitAll();
                                // })
                                .oauth2Login(Customizer.withDefaults())
                                .authorizeHttpRequests(authorize -> {
                                        authorize
                                                        .requestMatchers(HttpMethod.GET, "/users/**").hasRole("ADMIN");
                                        authorize
                                                        .requestMatchers(HttpMethod.POST, "/users/**").permitAll();
                                        authorize
                                                        .requestMatchers(HttpMethod.POST, "/categories/**").permitAll();
                                        authorize
                                                        .requestMatchers("/orders/**").hasAnyRole("ADMIN", "USER");
                                        authorize
                                                        .anyRequest().authenticated();
                                })
                                .build();
        }

        @Bean
        public PasswordEncoder encoder() {
                return new BCryptPasswordEncoder();
        }

        // @Bean
        public UserDetailsService userDetailsService(UserService userService) {
                return new CustomUserDetailService(userService);
        }
}
