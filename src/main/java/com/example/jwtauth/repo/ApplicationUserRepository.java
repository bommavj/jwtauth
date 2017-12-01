package com.example.jwtauth.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.jwtauth.model.ApplicationUser;

public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}