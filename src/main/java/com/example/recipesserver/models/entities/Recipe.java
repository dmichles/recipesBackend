package com.example.recipesserver.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long recipeId;

    private String name;

    private String servings;

    private String timeInAdvance;

    private String imageUrl;

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "rating_id")
    private Rating rating;

    private String comments;

    private String source;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cuisine_id")
    private Cuisine cuisine;

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "prep_id")
    private PrepMethod prepMethod;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "type_id")
    private Type type;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<RecipeIngredient> RecipeIngredients = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<RecipePrepStep> recipePrepStep = new ArrayList<>();

    public Recipe() {}


    public Long getRecipeId() {
        return recipeId;
    }

    public String getName() {
        return name;
    }



    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<RecipeIngredient> getRecipeIngredients() {
        return RecipeIngredients;
    }

    public void setIngredients(Set<RecipeIngredient> RecipeIngredients) {
        this.RecipeIngredients = RecipeIngredients;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getTimeInAdvance() {
        return timeInAdvance;
    }

    public void setTimeInAdvance(String timeInAdvance) {
        this.timeInAdvance = timeInAdvance;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public PrepMethod getPrepMethod() {
        return prepMethod;
    }

    public void setPrepMethod(PrepMethod prepMethod) {
        this.prepMethod = prepMethod;
    }

    public void setRecipeIngredients(Set<RecipeIngredient> recipeIngredients) {
        RecipeIngredients = recipeIngredients;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setRecipePrepStep(List<RecipePrepStep> recipePrepStep) {
        this.recipePrepStep = recipePrepStep;
    }

    public List<RecipePrepStep> getRecipePrepStep() {
        return recipePrepStep;
    }

    public String getImageUrl() {
        if (imageUrl == null) {
            System.out.println("null");
        }
        System.out.println("imageUrl: " + imageUrl);
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
