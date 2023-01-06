package com.wmk.demo.repository;

import com.wmk.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);
}