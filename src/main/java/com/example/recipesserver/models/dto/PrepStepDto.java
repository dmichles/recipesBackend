package com.example.recipesserver.models.dto;

public class PrepStepDto {
    private String id;

    private String prepStep;

    public PrepStepDto() {}

    public String getPrepStep() {
        return prepStep;
    }

    public void setPrepStep(String prepStep) {
        this.prepStep = prepStep;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PrepStepDto{" +
                "id='" + id + '\'' +
                "prepStep='" + prepStep + '\'' +
                '}';
    }
}
