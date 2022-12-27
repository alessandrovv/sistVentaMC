package com.example.vvam.service;

import com.example.vvam.dto.SellerRegistrationDto;
import com.example.vvam.model.Seller;
import com.example.vvam.repository.ISellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements ISellerService{
    @Autowired
    private ISellerRepository sellerRepository;

    @Override
    public Seller save(SellerRegistrationDto registrationDto){
        Seller seller = null;
        if(sellerRepository.findByDni(registrationDto.getDni())==null){
            seller = new Seller(
                    registrationDto.getDni(),
                    registrationDto.getFirstName(),
                    registrationDto.getLastName(),
                    registrationDto.getEmail()
            );
        }

        return sellerRepository.save(seller);
    }

    @Override
    public Seller findByDni(String dni){
        return sellerRepository.findByDni(dni);
    }

    @Override
    public List<Seller> listAll(){
        return sellerRepository.findAll();
    }
}
