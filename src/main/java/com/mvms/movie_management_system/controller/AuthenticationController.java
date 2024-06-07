package com.mvms.movie_management_system.controller;

import com.mvms.movie_management_system.api.entity.LoginBody;
import com.mvms.movie_management_system.api.entity.LoginResponse;
import com.mvms.movie_management_system.api.entity.RegistrationBody;
import com.mvms.movie_management_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody registrationBody) {
        System.out.println("AuthenticationController - registerUser()");
        try {
            userService.registerUser(registrationBody);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody) {
        System.out.println("AuthenticationController - loginUser()");
        try {
            String jwt = userService.loginUser(loginBody);
            if (jwt == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else {
                LoginResponse response = new LoginResponse();
                response.setJwt(jwt);
                return ResponseEntity.ok(response);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
