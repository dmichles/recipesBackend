package com.example.recipesserver.models.dto;

public class CreateRecipeDto {
    private String message;
    public CreateRecipeDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
