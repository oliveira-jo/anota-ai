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

import com.oliveira.anotaai.domain.product.Product;
import com.oliveira.anotaai.domain.product.ProductDTO;
import com.oliveira.anotaai.services.ProductService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/product")
public class ProductController {

  private ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  public ResponseEntity<Product> insert(@RequestBody ProductDTO productData) {
    Product newProduct = this.productService.insert(productData);
    return ResponseEntity.ok().body(newProduct);
  }

  @GetMapping
  private ResponseEntity<List<Product>> getAll() {
    List<Product> products = this.productService.getAll();
    return ResponseEntity.ok().body(products);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> update(@PathParam("id") String id, @RequestBody ProductDTO productData) {
    Product newProduct = this.productService.update(id, productData);
    return ResponseEntity.ok().body(newProduct);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Product> delete(@PathParam("id") String id) {
    this.productService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
