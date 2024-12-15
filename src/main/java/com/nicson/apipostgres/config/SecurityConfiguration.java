package com.nicson.apipostgres.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nicson.apipostgres.security.CustomUserDetailService;
import com.nicson.apipostgres.security.LoginSocialSuccessHandler;
import com.nicson.apipostgres.services.UserService;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.jwk.RSAKey;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                        LoginSocialSuccessHandler loginSocialSuccessHandler) throws Exception {
                System.out.println("passou no errado");
                return httpSecurity
                                .csrf(AbstractHttpConfigurer::disable)
                                .httpBasic(Customizer.withDefaults())
                                .formLogin(configurer -> {
                                        configurer.loginPage("/login").permitAll();
                                })
                                .oauth2Login(Customizer.withDefaults())
                                .oauth2Login(oauth2 -> {
                                        oauth2
                                                        .loginPage("/login");
                                        oauth2
                                                        .successHandler(loginSocialSuccessHandler);
                                })
                                .oauth2ResourceServer(oauth2RS -> oauth2RS.jwt(Customizer.withDefaults()))
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
                                                        .requestMatchers("/login/**").permitAll();
                                        authorize
                                                        .anyRequest().permitAll();
                                })
                                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                                .oauth2ResourceServer(oauth2RS -> oauth2RS.jwt(Customizer.withDefaults()))
                                .build();
        }

        // @Bean
        // public PasswordEncoder passwordEncoder() {
        // return new BCryptPasswordEncoder(10);
        // }

        @Bean
        public GrantedAuthorityDefaults grantedAuthorityDefaults() {
                return new GrantedAuthorityDefaults("");
        }

        // CONFIGURA NO JWT O PREFIXO SCOPE
        @Bean
        public JwtAuthenticationConverter jwtAuthenticationConverter() {
                JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
                authoritiesConverter.setAuthorityPrefix("");

                JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
                converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

                return converter;
        }

}
