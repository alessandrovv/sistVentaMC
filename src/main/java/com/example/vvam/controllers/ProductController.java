package com.example.vvam.controllers;


import com.example.vvam.dto.ProductRegistrarionDto;
import com.example.vvam.model.Product;
import com.example.vvam.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
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
    
    @GetMapping("/add")
    public String addProduct(Product product, Model model){
        model.addAttribute("product", product);
        model.addAttribute("size", false);
        return "dashboard/product/save-update-product";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") long id, Model model){
        Product product = productService.getById(id).get();
        model.addAttribute("product", product);
        model.addAttribute("size", true);

        return "dashboard/product/save-update-product";
    }

    @PostMapping("/save")
    public String save(@RequestParam(value = "idProduct") Long id, @RequestParam(value = "name") String name, @RequestParam(value = "description") String description,
                       @RequestParam(value = "price") double price, @RequestParam(value = "stock") int stock, RedirectAttributes redirect) throws ParseException {
        Product product = null;
        if(id==null || id==0){
            product = productService.save(new ProductRegistrarionDto(name,description,price,stock));
            redirect.addFlashAttribute("message", "Cliente agregado correctamente.");
        }else{
            product = productService.save(id,new ProductRegistrarionDto(name,description,price,stock));
            redirect.addFlashAttribute("message", "Producto" + product.getName() + " actualizado correctamente.");
        }

        return "redirect:/product/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        Product product = productService.getById(id).get();
        productService.delete(id, new ProductRegistrarionDto(product.getName(),product.getDescription(), product.getPrice(), product.getStock()));
        return "redirect:/product/";
    }
}
