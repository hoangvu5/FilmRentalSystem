package com.mvms.movie_management_system.controller;

import com.mvms.movie_management_system.api.entity.LoginBody;
import com.mvms.movie_management_system.api.entity.RegistrationBody;
import com.mvms.movie_management_system.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String homePage() {
        System.out.println("MainController - index.html");
        return "index";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        System.out.println("MainController - register_form.html");
        RegistrationBody registrationBody = new RegistrationBody();
        model.addAttribute("registrationBody", registrationBody);
        return "register_form";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        System.out.println("MainController - login_form.html");
        LoginBody loginBody = new LoginBody();
        model.addAttribute("loginBody", loginBody);
        return "login_form";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        System.out.println("MainController - dashboard.html");
        return "dashboard";
    }
}
