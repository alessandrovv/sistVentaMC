package com.example.vvam;

import com.example.vvam.repository.*;
import com.example.vvam.dto.*;
import com.example.vvam.model.*;
import com.example.vvam.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class SetupData implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ISellerRepository sellerRepository;

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISellerService sellerService;

    @Autowired
    private IClientService clientService;

    @Autowired
    private IProductService productService;

    @Override
    @Transactional
    public void  onApplicationEvent(ContextRefreshedEvent event){
        if(alreadySetup)
            return;
        createRoleIfNotFound("ROLE_USER");
        createRoleIfNotFound("ROLE_ADMIN");
        Seller seller = createSellerIfNotFound(new SellerRegistrationDto("75268952","Juan","Perez","juan@gmail.com"));
        createUserAdminIfNotFound(new UserRegistrationDto("admin","admin", "admin@gmail.com", "123456"));
        createUserUserIfNotFound(new UserRegistrationDto("ventas","ventas", "ventas@gmail.com", "123456"), seller);
        createClientIfNotFound(new ClientRegistrationDto("74985052", "Alessandro","alessandro.venegas@gmail.com"));
        createClientIfNotFound(new ClientRegistrationDto("74985053", "Pedro","pedro.sanchez@gmail.com"));
        createProductIfNotFound(new ProductRegistrarionDto("Producto1","Producto1", 20.5, 20));
        createProductIfNotFound(new ProductRegistrarionDto("Producto2","Producto2", 15.3, 10));
        createProductIfNotFound(new ProductRegistrarionDto("Producto3","Producto3", 7.5, 16));
        createProductIfNotFound(new ProductRegistrarionDto("Producto4","Producto4", 11, 18));
    }

    @Transactional
    Role createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }

    @Transactional
    User createUserAdminIfNotFound(UserRegistrationDto registrationDto) {
        User user = userRepository.findByEmail(registrationDto.getUserName());
        if (user == null) {
            user = userService.saveAdmin(registrationDto);
        }
        return user;
    }

    @Transactional
    User createUserUserIfNotFound(UserRegistrationDto registrationDto, Seller seller) {
        User user = userRepository.findByEmail(registrationDto.getUserName());
        if (user == null) {
            registrationDto.setSeller(seller);
            user = userService.saveSeller(registrationDto);
        }
        return user;
    }

    @Transactional
    Seller createSellerIfNotFound(SellerRegistrationDto registrationDto){
        Seller seller = sellerRepository.findByDni(registrationDto.getDni());

        if(seller == null){
            seller = sellerService.save(registrationDto);
        }

        return seller;
    }

    @Transactional
    Client createClientIfNotFound(ClientRegistrationDto registrationDto){
        Client client = clientRepository.findByDocumentoIdentidad(registrationDto.getDocumentoIdentidad());

        if(client==null){
            client = clientService.save(registrationDto);
        }

        return client;
    }

    @Transactional
    Product createProductIfNotFound(ProductRegistrarionDto registrarionDto){
        Product product = productRepository.findByName(registrarionDto.getName());
        if(product ==null){
            product = productService.save(registrarionDto);
        }

        return product;
    }
}
