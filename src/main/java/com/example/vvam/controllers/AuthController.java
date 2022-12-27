package com.example.vvam.controllers;

import com.example.vvam.dto.UserRegistrationDto;
import com.example.vvam.model.User;
import com.example.vvam.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public String login(Model model){
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model){
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        model.addAttribute("userRegistrationDto",userRegistrationDto);

        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUserAccount(@Validated @ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto, BindingResult result, Model model){
        model.addAttribute("userRegistrationDto",userRegistrationDto);
        User userExists = userService.findByUsername(userRegistrationDto.getUserName());

        if(userExists !=null){
            return "redirect:/register?username";
        }
        if(result.hasErrors()){
            return "/auth/register";
        }
        userService.saveUser(userRegistrationDto);
        return "redirect:/register?success";
    }



}
