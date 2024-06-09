package com.mvms.movie_management_system.controller;

import com.mvms.movie_management_system.entity.Category;

import com.mvms.movie_management_system.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public List<Category> fetchCategories() {
        System.out.println("CategoryController - fetchCategories()");
        return categoryRepository.findAll();
    }

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category) {
        System.out.println("CategoryController - createCategory()");
        if ((category.getCategoryId() != null && categoryRepository.findById(category.getCategoryId()).isPresent()) ||
            categoryRepository.findByNameIgnoreCase(category.getName()).isPresent()) {
            throw new IllegalArgumentException("A category with the same ID or name has already existed.");
        } else {
            return categoryRepository.save(category);
        }
    }

    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
        System.out.println("CategoryController - updateCategory()");
        if (categoryRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Category[id = " + id + "] does not exist.");
        } else {
            return categoryRepository.save(category);
        }
    }

    @DeleteMapping("categories/{id}")
    public String deleteCategory(@PathVariable Long id, @RequestBody Category category) {
        System.out.println("CategoryController - deleteCategory()");
        if (categoryRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Category[id = " + id + "] does not exist.");
        } else {
            categoryRepository.deleteById(id);
            return "Category[id = " + id + "] was deleted!";
        }
    }

}
