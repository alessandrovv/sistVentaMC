package com.example.vvam.repository;

import com.example.vvam.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISaleRepository extends JpaRepository<Sale,Long> {

    List<Sale> findAllByEliminadoIsFalse();

}
