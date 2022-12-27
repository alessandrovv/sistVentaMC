package com.example.vvam.service;

import com.example.vvam.dto.SellerRegistrationDto;
import com.example.vvam.model.Seller;

import java.util.List;

public interface ISellerService {
    Seller save(SellerRegistrationDto sellerRegistrationDto);
    Seller findByDni(String dni);
    List<Seller> listAll();
}
