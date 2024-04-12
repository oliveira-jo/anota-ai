package com.oliveira.anotaai.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oliveira.anotaai.domain.category.Category;
import com.oliveira.anotaai.domain.category.exceptions.CategoryNotFoundException;
import com.oliveira.anotaai.domain.product.Product;
import com.oliveira.anotaai.domain.product.ProductDTO;
import com.oliveira.anotaai.domain.product.exceptions.ProductNotFoundException;

import com.oliveira.anotaai.repositories.ProductRepository;

@Service
public class ProductService {

  private CategoryService categoryService;
  private ProductRepository productRepository;

  public ProductService(CategoryService categoryService, ProductRepository productRepository) {
    this.categoryService = categoryService;
    this.productRepository = productRepository;
  }

  public Product insert(ProductDTO productData) {
    // Existing category
    Category category = this.categoryService.getById(productData.categoryId())
        .orElseThrow(CategoryNotFoundException::new);

    Product newProduct = new Product(productData);
    newProduct.setCategory(category);

    this.productRepository.save(newProduct);
    return newProduct;
  }

  public List<Product> getAll() {
    return productRepository.findAll();
  }

  public Product update(String id, ProductDTO productData) {
    Product product = this.productRepository.findById(id)
        .orElseThrow(ProductNotFoundException::new);

    // Existing category set the category
    this.categoryService.getById(productData.categoryId()).ifPresent(product::setCategory);

    if (!productData.title().isEmpty())
      product.setTitle(productData.title());
    if (!productData.description().isEmpty())
      product.setDescription(productData.description());
    if (!(productData.price() != null))
      product.setPrice(productData.price());

    // update
    this.productRepository.save(product);

    return product;
  }

  public void delete(String id) {
    Product product = this.productRepository.findById(id)
        .orElseThrow(ProductNotFoundException::new);

    // delete
    this.productRepository.delete(product);
    ;
  }
}
