package com.example.recipesserver.models.repositories;

import com.example.recipesserver.models.entities.Category;
import com.example.recipesserver.models.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findByName(String name);
    List<Recipe> findAll();
    List<Recipe> findAllByCategory(Category category);
}
