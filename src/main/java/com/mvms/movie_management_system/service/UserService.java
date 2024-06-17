package com.mvms.movie_management_system.service;

import com.mvms.movie_management_system.api.entity.LoginBody;
import com.mvms.movie_management_system.api.entity.RegistrationBody;
import com.mvms.movie_management_system.entity.User;
import com.mvms.movie_management_system.exception.custom.RecordFoundException;
import com.mvms.movie_management_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private EncryptionService encryptionService;

    public UserService(UserRepository userRepository, EncryptionService encryptionService) {
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
    }

    public User registerUser(RegistrationBody registrationBody) {
        if (userRepository.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent() ||
            userRepository.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()) {
            throw new RecordFoundException("An user with the same username or email has already existed.");
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

    public User loginUser(LoginBody loginBody) {
        Optional<User> dbUser = userRepository.findByUsernameIgnoreCase(loginBody.getUsername());
        if (dbUser.isPresent()) {
            User user = dbUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
