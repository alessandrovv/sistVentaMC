package com.example.vvam.service;

import com.example.vvam.dto.ProductRegistrarionDto;
import com.example.vvam.model.Client;
import com.example.vvam.model.Product;
import com.example.vvam.repository.IClientRepository;
import com.example.vvam.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements IProductService{
    @Autowired
    private IProductRepository productRepository;

    public Product save(ProductRegistrarionDto registrarionDto){
        Product product = null;
        if(productRepository.findByName(registrarionDto.getName())==null){
            product = new Product(
                    registrarionDto.getName(),
                    registrarionDto.getDescription(),
                    registrarionDto.getPrice(),
                    registrarionDto.getStock()
            );
        }
        return productRepository.save(product);
    }

    public Product findByName(String name){
        return productRepository.findByName(name);
    }

    public List<Product> listAll(){
        return productRepository.findAll();
    }
}
