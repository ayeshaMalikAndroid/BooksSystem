package com.example.books.service;

import com.example.books.entities.Category;
import com.example.books.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    // Method to create a category
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Method to get all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Method to get a category by ID
    public Category getCategoryById(String id) {
        return categoryRepository.findById(id).orElse(null);
    }

    // Method to update a category by ID
    public Category updateCategory(String id, Category category) {
        category.setId(id);
        return categoryRepository.save(category);
    }

    // Method to delete a category by ID
    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }
}
