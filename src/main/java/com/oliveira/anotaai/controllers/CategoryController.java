package com.oliveira.anotaai.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oliveira.anotaai.domain.category.Category;
import com.oliveira.anotaai.domain.category.CategoryDTO;
import com.oliveira.anotaai.services.CategoryService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

  private CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @PostMapping
  public ResponseEntity<Category> insert(@RequestBody CategoryDTO categoryDTO) {
    Category newCategory = this.categoryService.insert(categoryDTO);
    return ResponseEntity.ok().body(newCategory);
  }

  @GetMapping
  private ResponseEntity<List<Category>> getAll() {
    List<Category> categories = this.categoryService.getAll();
    return ResponseEntity.ok().body(categories);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Category> update(@PathParam("id") String id, @RequestBody CategoryDTO categoryDTO) {
    Category newCategory = this.categoryService.update(id, categoryDTO);
    return ResponseEntity.ok().body(newCategory);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Category> delete(@PathParam("id") String id) {
    this.categoryService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
