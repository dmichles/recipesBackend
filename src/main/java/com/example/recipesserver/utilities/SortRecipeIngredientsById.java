package com.example.recipesserver.utilities;


import com.example.recipesserver.models.entities.RecipeIngredient;

import java.util.Comparator;

public class SortRecipeIngredientsById implements Comparator<RecipeIngredient> {
    @Override
    public int compare(RecipeIngredient o1, RecipeIngredient o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
