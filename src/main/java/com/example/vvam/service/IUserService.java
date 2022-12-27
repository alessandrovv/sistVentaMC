package com.example.vvam.service;

import com.example.vvam.dto.UserRegistrationDto;
import com.example.vvam.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    User saveAdmin(UserRegistrationDto registrationDto);
    User saveUser(UserRegistrationDto registrationDto);
    User findByUsername(String username);
}
