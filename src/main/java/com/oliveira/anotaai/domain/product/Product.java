package com.oliveira.anotaai.domain.product;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.oliveira.anotaai.domain.category.Category;

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
    json.put("id", id);
    json.put("title", title);
    json.put("description", description);
    json.put("ownerId", ownerId);
    json.put("price", price);
    json.put("category", category);
    json.put("type", "produto");

    return json.toString();
  }

  public String deleteToString() {
    JSONObject json = new JSONObject();
    json.put("id", this.id);
    json.put("ownerId", this.ownerId);
    json.put("type", "delete-produto");

    return json.toString();
  }

}
