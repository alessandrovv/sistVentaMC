package com.example.vvam.service;

import com.example.vvam.dto.SaleRegistrationDto;
import com.example.vvam.model.Sale;
import com.example.vvam.model.Sale_Detail;
import com.example.vvam.repository.IClientRepository;
import com.example.vvam.repository.ISaleRepository;
import com.example.vvam.repository.ISellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements ISaleService{
    @Autowired
    private ISaleRepository saleRepository;

    @Autowired
    private ISellerRepository sellerRepository;

    @Autowired
    private IClientRepository clientRepository;

    @Override
    public Sale save(SaleRegistrationDto registrationDto){
        Sale sale = new Sale(
                registrationDto.getDate(),
                registrationDto.getSeller(),
                registrationDto.getClient()
        );

        return saleRepository.save(sale);
    }

    @Override
    public Sale save(Long id, SaleRegistrationDto registrationDto){
        Sale sale = new Sale(
                id,
                registrationDto.getDate(),
                registrationDto.getSeller(),
                registrationDto.getClient()
        );

        return saleRepository.save(sale);
    }

    @Override
    public Sale save(SaleRegistrationDto registrationDto, Collection<Sale_Detail> sale_details){
        Sale sale = new Sale(
                registrationDto.getDate(),
                registrationDto.getSeller(),
                registrationDto.getClient(),
                sale_details
        );

        return saleRepository.save(sale);
    }
    @Override
    public Sale save(Long id,SaleRegistrationDto registrationDto, Collection<Sale_Detail> sale_details){
        Sale sale = new Sale(
                id,
                registrationDto.getDate(),
                registrationDto.getSeller(),
                registrationDto.getClient(),
                sale_details
        );

        return saleRepository.save(sale);
    }

    @Override
    public Sale delete(Long id, SaleRegistrationDto registrationDto){
        Sale sale = new Sale(
                id,
                registrationDto.getDate(),
                registrationDto.getSeller(),
                registrationDto.getClient(),
                true
        );

        return saleRepository.save(sale);
    }

    public Optional<Sale> getById(Long id){
        return saleRepository.findById((id));
    }

    public List<Sale> listAll(){
        return saleRepository.findAllByEliminadoIsFalse();
    }
}
