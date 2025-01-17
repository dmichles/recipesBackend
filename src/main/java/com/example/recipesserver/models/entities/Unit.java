package com.example.recipesserver.models.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "unit")
public class Unit {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "unit_id")
    private Long unitId;

    private String unitOfMeasure;

    @OneToMany
    private Set<RecipeIngredient> RecipeIngredients = new HashSet<>();

    public Unit() {}

    public Long getUnitId() {
        return unitId;
    }

    public void setId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Set<RecipeIngredient> getRecipeIngredients() {
        return RecipeIngredients;
    }

    public void setRecipeIngredients(Set<RecipeIngredient> recipeIngredients) {
        RecipeIngredients = recipeIngredients;
    }
}
