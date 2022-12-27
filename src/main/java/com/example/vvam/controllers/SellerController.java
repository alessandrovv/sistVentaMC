package com.example.vvam.controllers;

import com.example.vvam.model.Product;
import com.example.vvam.model.Seller;
import com.example.vvam.service.SellerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private SellerServiceImpl sellerService;

    @GetMapping("")
    public String listSellers(Model model){
        List<Seller> sellers = sellerService.listAll();
        model.addAttribute("listSellers", sellers);

        return "dashboard/seller/list-seller";
    }
}
