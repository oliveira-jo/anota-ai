package com.oliveira.anotaai.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oliveira.anotaai.domain.category.Category;
import com.oliveira.anotaai.domain.category.CategoryDTO;
import com.oliveira.anotaai.domain.category.exceptions.CategoryNotFoundException;
import com.oliveira.anotaai.repositories.CategoryRepository;

@Service
public class CategoryService {

  private CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public Category insert(CategoryDTO categoryData) {
    Category newCategory = new Category(categoryData);
    this.categoryRepository.save(newCategory);
    return newCategory;
  }

  public List<Category> getAll() {
    return categoryRepository.findAll();
  }

  public Optional<Category> getById(String id) {
    return categoryRepository.findById(id);
  }

  public Category update(String id, CategoryDTO categoryData) {
    Category category = this.categoryRepository.findById(id)
        .orElseThrow(CategoryNotFoundException::new);

    if (!categoryData.title().isEmpty())
      category.setTitle(categoryData.title());
    if (!categoryData.description().isEmpty())
      category.setDescription(categoryData.description());

    // update
    this.categoryRepository.save(category);

    return category;
  }

  public void delete(String id) {
    Category category = this.categoryRepository.findById(id)
        .orElseThrow(CategoryNotFoundException::new);

    // delete
    this.categoryRepository.delete(category);
    ;
  }
}
