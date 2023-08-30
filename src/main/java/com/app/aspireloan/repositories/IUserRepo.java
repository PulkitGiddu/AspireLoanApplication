package com.app.aspireloan.repositories;

import com.app.aspireloan.models.User;

import java.util.List;

public interface IUserRepo {
    public User getUserById(String userId);
    public void addUser(User user);
    public List<User> getAllUsers();
}
