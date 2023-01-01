package com.example.vvam.service;

import com.example.vvam.dto.ClientRegistrationDto;
import com.example.vvam.model.Client;

import java.util.List;

public interface IClientService {
    Client save(ClientRegistrationDto registrationDto);
    Client save(Long id, ClientRegistrationDto registrationDto);

    Client delete(Long id, ClientRegistrationDto registrationDto);

    Client findByDocumentoIdentidad(String dni);

    List<Client> listAll();
}
