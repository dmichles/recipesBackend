package com.example.recipesserver.models.dto;

public class RecipeNameDto {
    private Long id;
    private String name;
    public RecipeNameDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RecipeNameDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
