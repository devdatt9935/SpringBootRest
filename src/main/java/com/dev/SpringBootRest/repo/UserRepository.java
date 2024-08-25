package com.dev.SpringBootRest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.SpringBootRest.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
}
