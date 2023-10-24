package com.currencyChange.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.currencyChange.demo.Dtos.PasswordUpdateDto;
import com.currencyChange.demo.Dtos.UserRegistrationDto;
import com.currencyChange.demo.Models.User;
import com.currencyChange.demo.Repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerAdmin(UserRegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRole("ROLE_ADMIN");
        return userRepository.save(user);
    }

    public boolean updatePassword(String username, PasswordUpdateDto passwordUpdateDto) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null && passwordEncoder.matches(passwordUpdateDto.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(passwordUpdateDto.getNewPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
