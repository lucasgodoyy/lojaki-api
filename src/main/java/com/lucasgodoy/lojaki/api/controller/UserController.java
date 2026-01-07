package com.lucasgodoy.lojaki.api.controller;

import com.lucasgodoy.lojaki.domain.user.model.User;
import com.lucasgodoy.lojaki.application.service.UserService;
import java.util.List;
import java.util.UUID;

// Controller for User entity
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Creates a new user
    public void createUser(User user) {
        userService.createUser(user);
    }

    // Updates an existing user
    public void updateUser(User user) {
        userService.updateUser(user);
    }

    // Deletes a user
    public void deleteUser(User user) {
        userService.deleteUser(user);
    }

    // Finds a user by ID
    public User getUserById(UUID id) {
        return userService.getUserById(id);
    }

    // Lists all users
    public List<User> listAllUsers() {
        return userService.listAllUsers();
    }
}
