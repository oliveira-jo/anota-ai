package com.oliveira.anotaai.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.oliveira.anotaai.domain.category.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

}
