package com.example.recipesserver.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "recipe_prep_step")
public class RecipePrepStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_prep_step_id")
    private Long recipePrepStepId;

    @Column(columnDefinition="text", length=10485760)
    private String prepStepName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public RecipePrepStep() {}

    public Long getRecipePrepStepId() {
        return recipePrepStepId;
    }

    public void setPrepStepId(Long recipePrepStepId) {
        this.recipePrepStepId = recipePrepStepId;
    }

    public String getPrepStepName() {
        return prepStepName;
    }

    public void setPrepStepName(String prepStepName) {
        this.prepStepName = prepStepName;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
