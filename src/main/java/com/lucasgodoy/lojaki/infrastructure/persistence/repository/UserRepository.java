package com.lucasgodoy.lojaki.infrastructure.persistence.repository;

import com.lucasgodoy.lojaki.domain.user.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for User entities.
 *
 * Defines the domain-level operations for managing users.
 * This interface is part of the domain layer and has no
 * dependency on frameworks, persistence, or infrastructure.
 *
 * Responsibilities:
 * - Retrieve users by ID or email
 * - Verify email uniqueness
 * - Save or update user entities
 * - List administrative users
 */
public interface UserRepository {

    /**
     * Finds a user by their email address.
     *
     * Only returns active users.
     *
     * @param email The email of the user to search for
     * @return Optional containing the user if found, empty otherwise
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks if a user exists with the given email.
     *
     * Useful to validate email uniqueness before creating a new user.
     *
     * @param email Email to check
     * @return true if the email is already used, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Saves a user entity.
     *
     * If the user is new, creates it. If it exists, updates the entity.
     *
     * @param user User entity to save
     * @return The persisted user entity
     */
    User save(User user);

    /**
     * Finds a user by their unique identifier.
     *
     * Only returns active users.
     *
     * @param id UUID of the user
     * @return Optional containing the user if found, empty otherwise
     */
    Optional<User> findById(UUID id);

    /**
     * Lists all users with administrative privileges.
     *
     * Only returns active users with role ADMIN.
     *
     * @return List of administrative users
     */
    List<User> findAllAdmins();
}
