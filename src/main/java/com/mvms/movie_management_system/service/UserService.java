package com.mvms.movie_management_system.service;

import com.mvms.movie_management_system.api.entity.LoginBody;
import com.mvms.movie_management_system.api.entity.RegistrationBody;
import com.mvms.movie_management_system.entity.User;
import com.mvms.movie_management_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private EncryptionService encryptionService;
    private JWTService jwtService;

    public UserService(UserRepository userRepository, EncryptionService encryptionService, JWTService jwtService) {
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }

    public User registerUser(RegistrationBody registrationBody) {
        if (userRepository.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent() ||
            userRepository.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()) {
            throw new IllegalArgumentException("An user with the same username or email has already existed.");
        }

        User user = new User();
        user.setUsername(registrationBody.getUsername());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        user.setEmail(registrationBody.getEmail());
        user.getCustomer().setFirstName(registrationBody.getFirstName());
        user.getCustomer().setLastName(registrationBody.getLastName());
        user.getCustomer().setUser(user);

        return userRepository.save(user);
    }

    public String loginUser(LoginBody loginBody) {
        Optional<User> dbUser = userRepository.findByUsernameIgnoreCase(loginBody.getUsername());
        if (dbUser.isPresent()) {
            User user = dbUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }
}
