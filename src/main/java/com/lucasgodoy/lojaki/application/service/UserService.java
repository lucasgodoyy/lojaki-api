package com.lucasgodoy.lojaki.application.service;

import com.lucasgodoy.lojaki.domain.user.model.User;
import com.lucasgodoy.lojaki.domain.repository.UserRepository;
import java.util.List;
import java.util.UUID;

// Service layer for User entity
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Creates a new user
    public void createUser(User user) {
        userRepository.save(user);
    }

    // Updates an existing user
    public void updateUser(User user) {
        userRepository.save(user);
    }

    // Deletes a user
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    // Finds a user by ID
    public User getUserById(UUID id) {
        return userRepository.findById(id);
    }

    // Lists all users
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }
}
