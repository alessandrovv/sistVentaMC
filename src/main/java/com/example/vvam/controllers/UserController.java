package com.example.vvam.controllers;

import com.example.vvam.dto.UserRegistrationDto;
import com.example.vvam.model.User;
import com.example.vvam.service.IUserService;
import com.example.vvam.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("")
    public String listUsers(Model model){
        List<User> users = userService.listAll();

        model.addAttribute("listUsers", users);
        return "dashboard/user/list-user";
    }
    /*
    @GetMapping("profile")
    public String userProfile(Model model){
        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetail = (UserDetails) auth;
        String email = userDetail.getUsername();
    }*/

    @GetMapping("add")
    public String addUser(User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("size", false);
        return "dashboard/user/save-update-user";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model){
        User user = userService.getById(id).get();
        model.addAttribute("user", user);
        model.addAttribute("size", true);

        return "dashboard/user/save-update-user";
    }
}
