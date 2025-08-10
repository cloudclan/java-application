package com.project.health_care.Service;

import com.project.health_care.Models.Customer;
import com.project.health_care.Reposotory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Customer registerUser(String username, String password) {
        Customer user = new Customer();
        Optional<Customer> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public Optional<Customer> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
