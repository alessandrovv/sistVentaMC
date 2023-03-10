package com.example.vvam.service;

import com.example.vvam.dto.ProductRegistrarionDto;
import com.example.vvam.model.Product;

import java.util.List;

public interface IProductService {
    Product save(ProductRegistrarionDto productRegistrarionDto);
    Product findByName(String name);
    List<Product> listAll();
}
