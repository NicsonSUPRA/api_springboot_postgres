package com.nicson.apipostgres.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.nicson.apipostgres.models.User;
import com.nicson.apipostgres.services.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String senhaDigitada = authentication.getCredentials().toString();

        User usuarioEncontrado = userService.obterPorLogin(login);

        if (usuarioEncontrado == null) {
            throw new UsernameNotFoundException("Usuario ou senha incorretos");
        }

        String senhaCriptografada = usuarioEncontrado.getPassword();

        boolean senhasBatem = passwordEncoder.matches(senhaDigitada, senhaCriptografada);

        if (senhasBatem) {
            return new CustomAuthentication(usuarioEncontrado);
        } else {
            throw new UsernameNotFoundException("Usuario ou senha incorretos");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }

}
