package com.example.vvam.service;

import com.example.vvam.dto.ClientRegistrationDto;
import com.example.vvam.model.Client;
import com.example.vvam.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService{

    @Autowired
    private IClientRepository clientRepository;

    @Override
    public Client save(ClientRegistrationDto registrationDto){
        Client client = null;

        if(clientRepository.findByDocumentoIdentidad(registrationDto.getDocumentoIdentidad())==null){
            client = new Client(
                    registrationDto.getDocumentoIdentidad(),
                    registrationDto.getRazonSocial(),
                    registrationDto.getEmail()
            );
        }

        return clientRepository.save(client);
    }
    @Override
    public Client save(Long id, ClientRegistrationDto registrationDto){
        Client client = new Client(
                id,
                registrationDto.getDocumentoIdentidad(),
                registrationDto.getRazonSocial(),
                registrationDto.getEmail());

        return clientRepository.save(client);
    }

    @Override
    public Client delete(Long id, ClientRegistrationDto registrationDto){
        Client client = new Client(
                id,
                registrationDto.getDocumentoIdentidad(),
                registrationDto.getRazonSocial(),
                registrationDto.getEmail(),
                false,
                true
        );
        return clientRepository.save(client);
    }

    @Override
    public Client findByDocumentoIdentidad(String dni){
        return clientRepository.findByDocumentoIdentidad(dni);
    }

    @Override
    public List<Client> listAll(){
        return clientRepository.findAllByEliminadoIsFalse();
    }

    public Optional<Client> getById(Long id){
        return clientRepository.findById(id);
    }
}
