package com.example.vvam.service;

import com.example.vvam.dto.SaleDetailRegistrationDto;
import com.example.vvam.dto.SaleRegistrationDto;
import com.example.vvam.model.Sale_Detail;
import com.example.vvam.repository.ISaleDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Sale_DetailServiceImpl implements ISale_DetailService{
    @Autowired
    private ISaleDetailRepository saleDetailRepository;

    @Override
    public Sale_Detail save(SaleDetailRegistrationDto registrationDto) {
        Sale_Detail sale_detail = new Sale_Detail(
                registrationDto.getSale(),
                registrationDto.getProduct(),
                registrationDto.getPrice(),
                registrationDto.getQuantity()
        );

        return saleDetailRepository.save(sale_detail);
    }

    @Override
    public Sale_Detail save(Long id, SaleDetailRegistrationDto registrationDto){
        Sale_Detail sale_detail = new Sale_Detail(
                id,
                registrationDto.getSale(),
                registrationDto.getProduct(),
                registrationDto.getPrice(),
                registrationDto.getQuantity(),
                registrationDto.isEliminado()
        );

        return saleDetailRepository.save(sale_detail);
    }
}
