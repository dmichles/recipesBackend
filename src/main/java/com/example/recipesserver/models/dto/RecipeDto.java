package com.example.recipesserver.models.dto;

import java.util.List;

public class RecipeDto {
    private String id;
    private String name;
    private String category;
    private String subcategory;
    private String cuisine;
    private String rating;
    private String servings;
    private String advance;
    private String source;
    private String comments;
    private String prepMethod;
    private String type;
    private String imageUrl;

    private List<IngredientDto> ingredients;
    private List<PrepStepDto> prepSteps;

    public RecipeDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getAdvance() {
        return advance;
    }

    public void setAdvance(String advance) {
        this.advance = advance;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPrepMethod() {
        return prepMethod;
    }

    public void setPrepMethod(String prepMethod) {
        this.prepMethod = prepMethod;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrepSteps(List<PrepStepDto> prepSteps) {
        this.prepSteps = prepSteps;
    }

    public List<PrepStepDto> getPrepSteps() {
        return prepSteps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    @Override
    public String toString() {
        return "RecipeDto{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", rating='" + rating + '\'' +
                ", servings='" + servings + '\'' +
                ", advance='" + advance + '\'' +
                ", source='" + source + '\'' +
                ", comments='" + comments + '\'' +
                ", prepMethod='" + prepMethod + '\'' +
                ", type='" + type + '\'' +
                ", ingredients=" + ingredients +
                ", prepSteps=" + prepSteps +
                '}';
    }
}
