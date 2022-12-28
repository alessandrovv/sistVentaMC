package com.example.vvam.service;

import com.example.vvam.dto.SellerRegistrationDto;
import com.example.vvam.model.Seller;
import com.example.vvam.repository.ISellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Seller save(Long id, SellerRegistrationDto registrationDto){
        Seller seller = new Seller(
                id,
                registrationDto.getDni(),
                registrationDto.getFirstName(),
                registrationDto.getLastName(),
                registrationDto.getEmail(), true);

        return sellerRepository.save(seller);
    }
    
    @Override
    public Seller delete(Long id, SellerRegistrationDto registrationDto){
        Seller seller = new Seller(
                id,
                registrationDto.getDni(),
                registrationDto.getFirstName(),
                registrationDto.getLastName(),
                registrationDto.getEmail(),
                false
        );
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
    
    public Optional<Seller> getById(Long id){
        return sellerRepository.findById(id);
    }
}
