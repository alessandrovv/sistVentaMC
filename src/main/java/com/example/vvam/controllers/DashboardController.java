package com.example.vvam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/sales")
    public String sales(){
        return "dashboard/sales";
    }
}
