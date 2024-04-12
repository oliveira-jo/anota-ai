package com.oliveira.anotaai.domain.category;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collation = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {
  @Id
  private String id;
  private String title;
  private String description;
  private String ownerId;

  public Category(CategoryDTO categoryDTO) {
    this.title = categoryDTO.title();
    this.description = categoryDTO.description();
    this.ownerId = categoryDTO.ownerId();
  }
}
