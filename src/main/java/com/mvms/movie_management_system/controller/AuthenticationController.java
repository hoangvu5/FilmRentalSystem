package com.mvms.movie_management_system.controller;

import com.mvms.movie_management_system.api.entity.LoginBody;
import com.mvms.movie_management_system.api.entity.LoginResponse;
import com.mvms.movie_management_system.api.entity.RegistrationBody;
import com.mvms.movie_management_system.entity.User;
import com.mvms.movie_management_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationBody registrationBody) {
        System.out.println("AuthenticationController - registerUser()");
        userService.registerUser(registrationBody);
        return ResponseEntity.ok().body(Map.of("redirectUrl", "/dashboard"));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody) {
        System.out.println("AuthenticationController - loginUser()");
        String jwt = userService.loginUser(loginBody);
        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            LoginResponse response = new LoginResponse();
            response.setJwt(jwt);
            return ResponseEntity.ok(response);
        }
    }
//
//    @GetMapping("/me")
//    public User getLoggedInUserProfile(@AuthenticationPrincipal User user) {
//        return user;
//    }
}
