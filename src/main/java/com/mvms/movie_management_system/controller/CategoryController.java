package com.mvms.movie_management_system.controller;

import com.mvms.movie_management_system.entity.Category;

import com.mvms.movie_management_system.exception.custom.RecordFoundException;
import com.mvms.movie_management_system.exception.custom.RecordNotFoundException;
import com.mvms.movie_management_system.repository.CategoryRepository;
import com.mvms.movie_management_system.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> fetchCategories() {
        System.out.println("CategoryController - fetchCategories()");
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        System.out.println("CategoryController - createCategory()");
        if ((category.getCategoryId() != null && categoryRepository.findById(category.getCategoryId()).isPresent()) ||
            categoryRepository.findByNameIgnoreCase(category.getName()).isPresent()) {
            throw new RecordFoundException("A category with the same ID or name has already existed.");
        } else {
            return ResponseEntity.ok(categoryRepository.save(category));
        }
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @Valid @RequestBody Category category) {
        System.out.println("CategoryController - updateCategory()");
        Optional<Category> dbCategory = categoryService.getCategoryById(id);
        if (dbCategory.isEmpty()) {
            throw new RecordNotFoundException("Category[id = " + id + "] does not exist.");
        } else {
            return ResponseEntity.ok(categoryService.updateCategory(id,category));
        }
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        System.out.println("CategoryController - deleteCategory()");
        if (categoryRepository.findById(id).isEmpty()) {
            throw new RecordNotFoundException("Category[id = " + id + "] does not exist.");
        } else {
            categoryRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }
}
