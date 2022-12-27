package com.example.vvam.service;

import com.example.vvam.dto.SaleRegistrationDto;
import com.example.vvam.model.Sale;
import com.example.vvam.model.Sale_Detail;

import java.util.Collection;

public interface ISaleService {
    Sale save(SaleRegistrationDto registrationDto);
    Sale save(Long id, SaleRegistrationDto registrationDto);

    Sale save(SaleRegistrationDto registrationDto, Collection<Sale_Detail> sale_details);
    Sale save(Long id, SaleRegistrationDto registrationDto, Collection<Sale_Detail> sale_details);

    Sale delete(Long id, SaleRegistrationDto registrationDto);
}
