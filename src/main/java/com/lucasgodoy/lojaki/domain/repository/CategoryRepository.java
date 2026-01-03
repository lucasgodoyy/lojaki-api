package com.lucasgodoy.lojaki.domain.repository;

import com.lucasgodoy.lojaki.domain.model.Category;
import java.util.UUID;
import java.util.List;

/**
 * Repository interface for Category entity.
 */
public interface CategoryRepository {

    /**
     * Finds a category by its ID.
     */
    Category findById(UUID id);

    /**
     * Saves a new category or updates an existing one.
     */
    void save(Category category);

    /**
     * Deletes a category.
     */
    void delete(Category category);

    /**
     * Returns all categories.
     */
    List<Category> findAll();
}
