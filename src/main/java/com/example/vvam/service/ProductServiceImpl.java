package com.example.vvam.service;

import com.example.vvam.dto.ProductRegistrarionDto;
import com.example.vvam.model.Product;
import com.example.vvam.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImpl implements IProductService{
    @Autowired
    private IProductRepository productRepository;
    
    @Override
    public Product save(ProductRegistrarionDto registrarionDto){
        Product product = null;
        if(productRepository.findByName(registrarionDto.getName())==null){
            product = new Product(
                    registrarionDto.getName(),
                    registrarionDto.getDescription(),
                    registrarionDto.getPrice(),
                    registrarionDto.getStock()
            );
        }
        return productRepository.save(product);
    }
    
    @Override
    public Product save(Long id, ProductRegistrarionDto registrarionDto){

          Product product = new Product(
            		id,
                    registrarionDto.getName(),
                    registrarionDto.getDescription(),
                    registrarionDto.getPrice(),
                    registrarionDto.getStock()
            );
        
        return productRepository.save(product);
    }
    
    @Override
    public Product delete(Long id, ProductRegistrarionDto registrarionDto){
        Product product = new Product(
                id,
                registrarionDto.getName(),
                registrarionDto.getDescription(),
                registrarionDto.getPrice(),
                registrarionDto.getStock(),
                false,
                true
        );
        return productRepository.save(product);
    }
    
	@Override
    public Product findByName(String name){
        return productRepository.findByName(name);
    }
	
	@Override
    public List<Product> listAll(){
        return productRepository.findAllByEliminadoIsFalse();
    }
    
    public Optional<Product> getById(Long id){
        return productRepository.findById(id);
    }
}
