package com.example.recipesserver.models.repositories;

import com.example.recipesserver.models.entities.RecipePrepStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RecipePrepStepRepository extends JpaRepository<RecipePrepStep, Long> {
    List<RecipePrepStep> findAllByRecipeName(String name);
}
