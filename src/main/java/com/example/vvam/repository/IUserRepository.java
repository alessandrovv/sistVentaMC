package com.example.vvam.repository;

import com.example.vvam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findAllByEliminadoIsFalse();
}
