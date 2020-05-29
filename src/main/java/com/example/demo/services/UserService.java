package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public void createUser(User user) {
        userRepo.createUser(user);
    }

}
