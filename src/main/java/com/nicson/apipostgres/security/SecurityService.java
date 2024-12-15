package com.nicson.apipostgres.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.nicson.apipostgres.models.User;
import com.nicson.apipostgres.services.UserService;

@Component
public class SecurityService {

    private final UserService userService;

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    public User obterUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // String email = userDetails.getUsername();
        // return userService.obterPorLogin(email);
        if (authentication instanceof CustomAuthentication customAuth) {
            return customAuth.getUser();
        } else {
            return null;
        }

    }
}
