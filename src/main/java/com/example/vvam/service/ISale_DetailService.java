package com.example.vvam.service;

import com.example.vvam.dto.SaleDetailRegistrationDto;
import com.example.vvam.dto.SaleRegistrationDto;
import com.example.vvam.model.Sale_Detail;

public interface ISale_DetailService {
    Sale_Detail save(SaleDetailRegistrationDto registrationDto);
    Sale_Detail save(Long id, SaleDetailRegistrationDto registrationDto);
}
