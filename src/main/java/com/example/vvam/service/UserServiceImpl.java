package com.example.vvam.service;

import com.example.vvam.model.*;
import com.example.vvam.dto.*;
import com.example.vvam.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(IUserRepository userRepository){
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User saveAdmin(UserRegistrationDto registrationDto) {
        User user = new User(
                registrationDto.getFirstName()
                ,registrationDto.getLastName()
                ,registrationDto.getUserName()
                ,passwordEncoder.encode(registrationDto.getPassword())
                , List.of(roleRepository.findByName("ROLE_ADMIN"))
        );

        return userRepository.save(user);
    }

    @Override
    public User saveUser(UserRegistrationDto registrationDto){
        User user = new User(
                registrationDto.getFirstName()
                ,registrationDto.getLastName()
                ,registrationDto.getUserName()
                ,passwordEncoder.encode(registrationDto.getPassword())
                , List.of(roleRepository.findByName("ROLE_USER"))
        );

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username){
        return userRepository.findByEmail(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(username);
        if(user ==null){
            throw new UsernameNotFoundException("Nombre de usuario o contrase??a inv??lidas.");
        }
        System.out.println(user.toString());
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
