package com.example.recipesserver.models.repositories;

import com.example.recipesserver.models.entities.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {
    @Query (nativeQuery = true, value = "SELECT DISTINCT r.* FROM recipe_ingredient r WHERE r.recipe_id = :id")
    List<RecipeIngredient> findAllByRecipeId(@Param("id") Long id);

    Optional<RecipeIngredient> findById(Long id);

}
