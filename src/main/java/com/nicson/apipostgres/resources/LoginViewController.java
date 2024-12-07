package com.nicson.apipostgres.resources;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String paginaHome(Authentication authentication) {
        return "Olá " + authentication.getName() + ", bem vindo(a) a Aplicação SpringBoot do Nicson!!";
    }
}
