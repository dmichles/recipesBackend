package com.example.recipesserver.models.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long ratingId;

    private String name;

    @OneToMany
    private Set<Recipe> recipes = new HashSet<>();

    public Rating() {
    }

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}
