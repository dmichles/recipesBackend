package com.example.recipesserver.models.dto;

public class PrepStepDto {
    private String prepStep;

    public PrepStepDto() {}

    public String getPrepStep() {
        return prepStep;
    }

    public void setPrepStep(String prepStep) {
        this.prepStep = prepStep;
    }

    @Override
    public String toString() {
        return "PrepStepDto{" +
                "prepStep='" + prepStep + '\'' +
                '}';
    }
}
