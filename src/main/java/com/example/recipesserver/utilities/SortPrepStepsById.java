package com.example.recipesserver.utilities;

import com.example.recipesserver.models.entities.Recipe;
import com.example.recipesserver.models.entities.RecipePrepStep;

import java.util.Comparator;

public class SortPrepStepsById implements Comparator<RecipePrepStep> {
    public int compare(RecipePrepStep a, RecipePrepStep b) {
        return a.getRecipePrepStepId().compareTo(b.getRecipePrepStepId());
    }
}
