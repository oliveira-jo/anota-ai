package com.oliveira.anotaai.domain.product;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import com.oliveira.anotaai.domain.category.Category;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
  @Id
  private String id;
  private String title;
  private String description;
  private String ownerId;
  // when it's save in the database we need to multiplicate for 100 to take off
  // the commas
  private Integer price;
  @DBRef
  private Category category;

  public Product(ProductDTO productDTO) {
    this.title = productDTO.title();
    this.description = productDTO.description();
    this.ownerId = productDTO.ownerId();
    this.price = productDTO.price();
  }

  @Override
  public String toString() {

    JSONObject json = new JSONObject();
    json.put("title", this.title);
    json.put("description", this.description);
    json.put("ownerId", this.ownerId);
    json.put("is", this.id);
    json.put("category", this.category);
    json.put("price", this.price);
    json.put("type", "product");

    return json.toString();
  }

}
