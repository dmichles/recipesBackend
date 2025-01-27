package com.example.recipesserver.models.dto;

public class RecipeNameDto {
    private String name;
    public RecipeNameDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RecipeNameDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
