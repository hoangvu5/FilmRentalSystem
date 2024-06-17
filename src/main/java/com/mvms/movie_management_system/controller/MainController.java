package com.mvms.movie_management_system.controller;

import com.mvms.movie_management_system.api.entity.LoginBody;
import com.mvms.movie_management_system.api.entity.LoginResponse;
import com.mvms.movie_management_system.api.entity.RegistrationBody;
import com.mvms.movie_management_system.entity.User;
import com.mvms.movie_management_system.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String homePage() {
        System.out.println("MainController - crudCustomers.html");
        return "index";
    }

    @GetMapping("/register")
    public String registerForm(HttpServletRequest request) {
        System.out.println("MainController - registerForm()");
        System.out.println("Session id = " + request.getRequestedSessionId());
        return "register_form";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegistrationBody registrationBody, HttpServletRequest request, Model model) {
        System.out.println("AuthenticationController - registerUser()");
        HttpSession session = request.getSession(true);
        try {
            User user = userService.registerUser(registrationBody);
            session.setAttribute("userSession", user);
            return "redirect:/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while processing your request. Please try again.");
            return "register_form";
        }
    }

    @GetMapping("/login")
    public String loginForm(HttpServletRequest request) {
        System.out.println("MainController - loginForm()");
        System.out.println("Session id = " + request.getRequestedSessionId());
        return "login_form";
    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute LoginBody loginBody, HttpServletRequest request, Model model) {
        System.out.println("MainController - loginUser()");
        HttpSession session = request.getSession(true);
        try {
            User user = userService.loginUser(loginBody);
            if (user != null) {
                session.setAttribute("userSession", user);
                return "redirect:/dashboard";
            } else {
                model.addAttribute("error", "Invalid username or password");
                return "login_form";
            }
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while processing your request. Please try again.");
            return "login_form";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request, Model model) {
        System.out.println("MainController - dashboard()");
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("userSession");

        model.addAttribute("username", user.getUsername());
        return "dashboard";
    }
    @GetMapping("/customers")
    public String customers() {
        return "crudCustomers";
    }
}
