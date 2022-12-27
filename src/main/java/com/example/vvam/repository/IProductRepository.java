package com.example.vvam.repository;

import com.example.vvam.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String name);
}
