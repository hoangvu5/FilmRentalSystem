package com.mvms.movie_management_system.service;
import com.mvms.movie_management_system.entity.Category;
import com.mvms.movie_management_system.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public Optional<Category> getCategoryById(Long id){
        return categoryRepository.findById(id);
    }
    public Category updateCategory(Long id, Category updatedCategory) {
        if (categoryRepository.findById(id).isPresent()) {
            Category existingCategory = categoryRepository.findById(id).get();
            existingCategory.setCategoryId(id);
            existingCategory.setFilmCategories(updatedCategory.getFilmCategories());
            existingCategory.setLastUpdate(updatedCategory.getLastUpdate());
            existingCategory.setName(updatedCategory.getName());
            return categoryRepository.save(existingCategory);
        } else {
            throw new RuntimeException("Category not found with id " + id);
        }
    }
}
