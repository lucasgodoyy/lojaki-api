package com.lucasgodoy.lojaki.application.service;

import com.lucasgodoy.lojaki.domain.model.Category;
import com.lucasgodoy.lojaki.domain.repository.CategoryRepository;
import java.util.List;
import java.util.UUID;

// Service layer for Category entity
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Creates a new category
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    // Updates an existing category
    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }

    // Deletes a category
    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    // Finds a category by ID
    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id);
    }

    // Lists all categories
    public List<Category> listAllCategories() {
        return categoryRepository.findAll();
    }
}
