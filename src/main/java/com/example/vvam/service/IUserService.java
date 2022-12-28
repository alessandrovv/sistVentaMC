package com.example.vvam.service;

import com.example.vvam.dto.UserRegistrationDto;
import com.example.vvam.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    User saveAdmin(UserRegistrationDto registrationDto);
    User saveUser(UserRegistrationDto registrationDto);
    User saveUser(Long id,UserRegistrationDto registrationDto);
    User delete(Long id,UserRegistrationDto registrationDto);
    User findByUsername(String username);
    List<User> listAll();
}
