package com.example.vvam.controllers;

import com.example.vvam.model.Product;
import com.example.vvam.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("")
    public String listProducts(Model model){
        List<Product> products = productService.listAll();
        model.addAttribute("listProducts",products);

        return "dashboard/product/list-product";
    }
}
