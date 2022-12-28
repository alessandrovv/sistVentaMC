package com.example.vvam.repository;

import com.example.vvam.model.Client;
import com.example.vvam.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String name);
    List<Product> findAllByEliminadoIsFalse();
}
