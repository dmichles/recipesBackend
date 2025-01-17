package com.example.recipesserver.models.repositories;

import com.example.recipesserver.models.entities.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {
    Set<RecipeIngredient> findAllByRecipeName(String name);
}
