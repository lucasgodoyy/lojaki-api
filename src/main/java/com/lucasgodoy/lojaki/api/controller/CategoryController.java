package com.lucasgodoy.lojaki.api.controller;

import com.lucasgodoy.lojaki.domain.model.Category;
import com.lucasgodoy.lojaki.application.service.CategoryService;
import java.util.List;
import java.util.UUID;

// Controller for Category entity
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Creates a new category
    public void createCategory(Category category) {
        categoryService.createCategory(category);
    }

    // Updates an existing category
    public void updateCategory(Category category) {
        categoryService.updateCategory(category);
    }

    // Deletes a category
    public void deleteCategory(Category category) {
        categoryService.deleteCategory(category);
    }

    // Finds a category by ID
    public Category getCategoryById(UUID id) {
        return categoryService.getCategoryById(id);
    }

    // Lists all categories
    public List<Category> listAllCategories() {
        return categoryService.listAllCategories();
    }
}
