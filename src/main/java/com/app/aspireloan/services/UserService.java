package com.app.aspireloan.services;

import com.app.aspireloan.models.User;
import com.app.aspireloan.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void addUser(User user) {
        userRepo.addUser(user);
        return;
    }

    public User getUser(String id) {
        return userRepo.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userRepo.getAllUsers();
    }
}
