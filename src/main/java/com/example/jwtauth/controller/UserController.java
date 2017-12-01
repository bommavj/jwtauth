package com.example.jwtauth.controller;

import javax.annotation.Resource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtauth.model.ApplicationUser;
import com.example.jwtauth.repo.ApplicationUserRepository;
import com.example.jwtauth.service.NextSequenceService;


@RestController
@RequestMapping("/users")
public class UserController {

    private ApplicationUserRepository applicationUserRepository;
    
    @Resource
    NextSequenceService service;
    
/*    @PostConstruct
    @Bean
    public BCryptPasswordEncoder createBC()
    {
    	return new BCryptPasswordEncoder();
    }*/
    
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(ApplicationUserRepository applicationUserRepository,
    		 BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
    	long seqNum = service.getNextSequence(user.getSeq());
    	user.setSeq(seqNum);
    	user.setId(seqNum);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }
    
}