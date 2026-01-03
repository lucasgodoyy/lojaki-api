package com.lucasgodoy.lojaki.domain.repository;

import com.lucasgodoy.lojaki.domain.model.User;
import java.util.UUID;
import java.util.List;

// Repository interface for User entity
public interface UserRepository {

    // Finds a user by ID
    User findById(UUID id);

    // Saves or updates a user
    void save(User user);

    // Deletes a user
    void delete(User user);

    // Returns all users
    List<User> findAll();
}
