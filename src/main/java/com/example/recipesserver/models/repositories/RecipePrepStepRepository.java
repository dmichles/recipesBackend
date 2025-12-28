package com.example.recipesserver.models.repositories;

import com.example.recipesserver.models.entities.RecipePrepStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RecipePrepStepRepository extends JpaRepository<RecipePrepStep, Long> {

    @Query(nativeQuery = true, value = "SELECT DISTINCT r.* FROM recipe_prep_step r WHERE r.recipe_id =:id")
    List<RecipePrepStep> findAllByRecipeId(@Param("id") Long id);
    void deleteAllByRecipeName(String name);
    Optional<RecipePrepStep> findByRecipePrepStepId(Long id);
}
