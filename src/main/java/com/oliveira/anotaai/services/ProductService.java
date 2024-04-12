package com.oliveira.anotaai.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oliveira.anotaai.domain.category.Category;
import com.oliveira.anotaai.domain.category.exceptions.CategoryNotFoundException;
import com.oliveira.anotaai.domain.product.Product;
import com.oliveira.anotaai.domain.product.ProductDTO;
import com.oliveira.anotaai.domain.product.exceptions.ProductNotFoundException;

import com.oliveira.anotaai.repositories.ProductRepository;
import com.oliveira.anotaai.services.aws.AwsSnsService;
import com.oliveira.anotaai.services.aws.MessageDTO;

@Service
public class ProductService {

  private final CategoryService categoryService;
  private final ProductRepository productRepository;
  private final AwsSnsService snsService;

  public ProductService(CategoryService categoryService, ProductRepository productRepository,
      AwsSnsService snsService) {
    this.categoryService = categoryService;
    this.productRepository = productRepository;
    this.snsService = snsService;
  }

  public Product insert(ProductDTO productData) {
    // Existing category
    Category category = this.categoryService.getById(productData.categoryId())
        .orElseThrow(CategoryNotFoundException::new);

    Product newProduct = new Product(productData);
    newProduct.setCategory(category);

    this.productRepository.save(newProduct);

    // publish in the topic
    this.snsService.publish(new MessageDTO(newProduct.toString()));

    return newProduct;

  }

  public List<Product> getAll() {
    return productRepository.findAll();

  }

  public Product update(String id, ProductDTO productData) {
    Product product = this.productRepository.findById(id)
        .orElseThrow(ProductNotFoundException::new);

    if (productData.categoryId() != null) {
      // Existing category set the category
      this.categoryService.getById(productData.categoryId()).ifPresent(product::setCategory);
    }

    if (!productData.title().isEmpty())
      product.setTitle(productData.title());
    if (!productData.description().isEmpty())
      product.setDescription(productData.description());
    if (!(productData.price() != null))
      product.setPrice(productData.price());

    // update
    this.productRepository.save(product);

    // publish in the topic
    this.snsService.publish(new MessageDTO(product.toString()));

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
