package com.currencyChange.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.currencyChange.demo.Models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
