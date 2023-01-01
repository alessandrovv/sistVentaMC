package com.example.vvam.repository;

import com.example.vvam.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IClientRepository extends JpaRepository<Client,Long> {
    Client findByDocumentoIdentidad(String docIdentidad);

    List<Client> findAllByEliminadoIsFalse();


}
