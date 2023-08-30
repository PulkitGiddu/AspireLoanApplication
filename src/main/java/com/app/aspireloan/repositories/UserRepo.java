package com.app.aspireloan.repositories;

import com.app.aspireloan.models.User;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("userRepoBean")
public class UserRepo implements IUserRepo {
    private Map<String, User> usersMap;

    private static UserRepo userRepo = new UserRepo();

    private UserRepo() {
        this.usersMap = new HashMap<>();
    }

    public static UserRepo getUserRepoInstance() {
        return userRepo;
    }

    /**
     * @param userId unique ID of the user
     * @return returns User object if present, null otherwise
     */
    public User getUserById(String userId) {
        if (usersMap.containsKey(userId)) {
            return usersMap.get(userId);
        }
        return null;
    }

    /**
     * @param user user which needs to be added to user repositoru
     * @return returns True if user is successfully added to repo, False if user is already present in repo.
     */
    public void addUser(User user) {
        user.setUserId(getNextUserId());
        usersMap.put(user.getUserId(), user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        List<String> keys = new ArrayList<>(usersMap.keySet());
        for (String key:keys) {
            users.add(usersMap.get(key));
        }
        return users;
    }

    private String getNextUserId() {
        return Integer.toString(usersMap.size()+1);
    }
}
