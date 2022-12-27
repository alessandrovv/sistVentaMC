package com.example.vvam.repository;

import com.example.vvam.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISellerRepository extends JpaRepository<Seller,Long> {
    Seller findByDni(String dni);
    
    
}
