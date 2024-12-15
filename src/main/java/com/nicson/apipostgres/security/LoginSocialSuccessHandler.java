package com.nicson.apipostgres.security;

import org.springframework.stereotype.Component;

import com.nicson.apipostgres.models.User;
import com.nicson.apipostgres.services.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Component
public class LoginSocialSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private UserService userService;

    public LoginSocialSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {

        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = authenticationToken.getPrincipal();

        String email = oAuth2User.getAttribute("email");

        User usuarioLogado = userService.obterPorLogin(email);

        CustomAuthentication customAuthentication = new CustomAuthentication(usuarioLogado);

        SecurityContextHolder.getContext().setAuthentication(customAuthentication);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
