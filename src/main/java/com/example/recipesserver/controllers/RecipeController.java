package com.example.recipesserver.controllers;


import com.example.recipesserver.models.dto.*;
import com.example.recipesserver.models.entities.*;
import com.example.recipesserver.models.repositories.*;
import com.example.recipesserver.utilities.SortPrepStepsById;
import com.example.recipesserver.utilities.SortRecipeIngredientsById;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RecipeController {
    private final RecipeIngredientRepository repository;
    private final IngredientRepository ingredientRepository;
    private final UnitRepository unitRepository;
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final TypeRepository typeRepository;
    private final RatingRepository ratingRepository;
    private final PrepMethodRepository prepMethodRepository;
    private final CuisineRepository cuisineRepository;
    private final RecipePrepStepRepository recipePrepStepRepository;

    public RecipeController(RecipeIngredientRepository repository, IngredientRepository ingredientRepository,
                            RecipeRepository recipeRepository, UnitRepository unitRepository,
                            CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository,
                            TypeRepository typeRepository,RatingRepository ratingRepository,
                            PrepMethodRepository prepMethodRepository, CuisineRepository cuisineRepository,
                            RecipePrepStepRepository recipePrepStepRepository) {
        this.repository = repository;
        this.ingredientRepository = ingredientRepository;
        this.unitRepository = unitRepository;
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.typeRepository = typeRepository;
        this.ratingRepository = ratingRepository;
        this.prepMethodRepository = prepMethodRepository;
        this.cuisineRepository = cuisineRepository;
        this.recipePrepStepRepository = recipePrepStepRepository;
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/deleteRecipe")
    @Transactional
    public ResponseEntity<?> deleteRecipe(@RequestParam String id){
        Recipe recipe = recipeRepository.findById(Long.valueOf(id)).get();

        List<RecipeIngredient> recipeIngredients = repository.findAllByRecipeId(Long.valueOf(id));
        recipeIngredients.forEach(recipeIngredient -> {
            repository.delete(recipeIngredient);
        });
        recipePrepStepRepository.findAllByRecipeId(Long.valueOf(id)).forEach(recipePrepStep -> {
            recipePrepStepRepository.delete(recipePrepStep);
        });
        recipeRepository.delete(recipe);
        System.out.println("recipe deleted"+id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*")
    @PostMapping ("/addRecipe")
    public ResponseEntity<?> createRecipe(@RequestBody RecipeDto recipeDto) {

        System.out.println(recipeDto);
        Recipe recipe = new Recipe();
        recipe.setName(recipeDto.getName());
        recipe.setImageUrl(recipeDto.getImageUrl());
        recipe.setComments(recipeDto.getComments());
        recipe.setServings(recipeDto.getServings());
        recipe.setSource(recipeDto.getSource());
        recipe.setTimeInAdvance(recipeDto.getAdvance());
        recipe.setCategory(categoryRepository.findByName(recipeDto.getCategory()));
        recipe.setSubcategory(subcategoryRepository.findByName(recipeDto.getSubcategory()));
        recipe.setType(typeRepository.findByName(recipeDto.getType()));
        recipe.setRating(ratingRepository.findByName(recipeDto.getRating()));
        recipe.setPrepMethod(prepMethodRepository.findByName(recipeDto.getPrepMethod()));
        recipe.setCuisine(cuisineRepository.findByName(recipeDto.getCuisine()));
        System.out.println(recipeDto.getCuisine());

        recipeDto.getPrepSteps().forEach(prepStep -> {
            RecipePrepStep recipePrepStep = new RecipePrepStep();
            recipePrepStep.setPrepStepName(prepStep.getPrepStep());
            recipePrepStep.setRecipe(recipe);
            recipePrepStepRepository.save(recipePrepStep);
        });

        recipeDto.getIngredients().forEach(ingredient -> {
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            if (ingredientRepository.findByName(ingredient.getName()) != null) {
                recipeIngredient.setIngredient(ingredientRepository.findByName(ingredient.getName().toLowerCase()));
                recipeIngredient.setUnit(unitRepository.findByUnitOfMeasure(ingredient.getUnit()));
                recipeIngredient.setQuantity(ingredient.getQuantity());
                recipeIngredient.setRecipe(recipe);
                repository.save(recipeIngredient);
            } else {
                Ingredient ing = new Ingredient();
                ing.setName(ingredient.getName());
                System.out.println(ing.getName());
                recipeIngredient.setIngredient(ing);
                recipeIngredient.setUnit(unitRepository.findByUnitOfMeasure(ingredient.getUnit()));
                recipeIngredient.setQuantity(ingredient.getQuantity());
                recipeIngredient.setRecipe(recipe);
                System.out.println(recipeIngredient.getIngredient().getName());
                repository.save(recipeIngredient);
            }
        });
        CreateRecipeDto createRecipeDto = new CreateRecipeDto();
        createRecipeDto.setMessage("Recipe created successfully");
        return ResponseEntity.ok(createRecipeDto);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/updateRecipe")
    @Transactional
    public ResponseEntity<?> updateRecipe(@RequestBody RecipeDto recipeDto) {
        System.out.println(recipeDto);
        Optional<Recipe> recipe = recipeRepository.findById(Long.valueOf(recipeDto.getId()));
        recipe.get().setName(recipeDto.getName());
        recipe.get().setImageUrl(recipeDto.getImageUrl());
        recipe.get().setComments(recipeDto.getComments());
        recipe.get().setServings(recipeDto.getServings());
        recipe.get().setSource(recipeDto.getSource());
        recipe.get().setTimeInAdvance(recipeDto.getAdvance());
        recipe.get().setCategory(categoryRepository.findByName(recipeDto.getCategory()));
        recipe.get().setSubcategory(subcategoryRepository.findByName(recipeDto.getSubcategory()));
        recipe.get().setType(typeRepository.findByName(recipeDto.getType()));
        recipe.get().setRating(ratingRepository.findByName(recipeDto.getRating()));
        recipe.get().setPrepMethod(prepMethodRepository.findByName(recipeDto.getPrepMethod()));
        recipe.get().setCuisine(cuisineRepository.findByName(recipeDto.getCuisine()));

        recipeDto.getPrepSteps().forEach(prepStep -> {
            Optional<RecipePrepStep> recipePrepStep = recipePrepStepRepository.findByRecipePrepStepId(Long.valueOf(prepStep.getId()));
            if (recipePrepStep.isPresent()) {
                recipePrepStep.get().setPrepStepName(prepStep.getPrepStep());
                recipePrepStep.get().setRecipe(recipe.get());
                recipePrepStepRepository.save(recipePrepStep.get());
            } else {
                RecipePrepStep recipePrepStep1 = new RecipePrepStep();
                recipePrepStep1.setPrepStepName(prepStep.getPrepStep());
                recipePrepStep1.setRecipe(recipe.get());
                recipePrepStepRepository.save(recipePrepStep1);
            }
        });

        recipeDto.getIngredients().forEach(ingredient -> {
            Optional<RecipeIngredient> recipeIngredient = repository.findById(Long.valueOf(ingredient.getId()));
            if (recipeIngredient.isPresent()) {
                System.out.println("here");
                System.out.println(ingredient.getQuantity());
                if(ingredientRepository.findByName(ingredient.getName()) != null) {
                    recipeIngredient.get().setIngredient(ingredientRepository.findByName(ingredient.getName().toLowerCase()));
                } else {
                    Ingredient newIngredient = new Ingredient();
                    newIngredient.setName(ingredient.getName());
                    ingredientRepository.save(newIngredient);
                    recipeIngredient.get().setIngredient(newIngredient);
                }
                recipeIngredient.get().setUnit(unitRepository.findByUnitOfMeasure(ingredient.getUnit()));
                recipeIngredient.get().setQuantity(ingredient.getQuantity());
                recipeIngredient.get().setRecipe(recipe.get());
                repository.save(recipeIngredient.get());
            } else {
                RecipeIngredient recipeIng = new RecipeIngredient();
                if (ingredientRepository.findByName(ingredient.getName()) != null) {
                    recipeIng.setIngredient(ingredientRepository.findByName(ingredient.getName()));
                } else {
                    Ingredient ing = new Ingredient();
                    ing.setName(ingredient.getName());
                    recipeIng.setIngredient(ing);
                }
                recipeIng.setUnit(unitRepository.findByUnitOfMeasure(ingredient.getUnit()));
                recipeIng.setQuantity(ingredient.getQuantity());
                recipeIng.setRecipe(recipe.get());
                System.out.println(recipeIng.getIngredient().getName());
                repository.save(recipeIng);
            }
        });
        CreateRecipeDto createRecipeDto = new CreateRecipeDto();
        createRecipeDto.setMessage("Recipe updated successfully");
        return ResponseEntity.ok(createRecipeDto);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getRecipe")
    public ResponseEntity<?> getRecipe(@RequestParam String id) {
        if (id == null || id.isEmpty() || recipeRepository.findById(Long.valueOf(id)).isEmpty()){
            return ResponseEntity.ok("");
        }
        Recipe recipe = recipeRepository.findById(Long.valueOf(id)).get();
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(String.valueOf(recipe.getRecipeId()));
        recipeDto.setName(recipe.getName());
        System.out.println(recipe.getImageUrl());
        recipeDto.setImageUrl(recipe.getImageUrl());
        recipeDto.setComments(recipe.getComments());
        recipeDto.setServings(recipe.getServings());
        recipeDto.setSource(recipe.getSource());
        recipeDto.setAdvance(recipe.getTimeInAdvance());
        recipeDto.setCategory(recipe.getCategory().getName());
        recipeDto.setSubcategory(recipe.getSubcategory().getName());
        recipeDto.setType(recipe.getType().getName());
        recipeDto.setRating(recipe.getRating().getName());
        recipeDto.setPrepMethod(recipe.getPrepMethod().getName());
        recipeDto.setCuisine(recipe.getCuisine().getName());
        List<IngredientDto> ingredients = new ArrayList<>();
        List<RecipeIngredient> recipeIngredients = repository.findAllByRecipeId(Long.valueOf(id));
        Collections.sort(recipeIngredients, new SortRecipeIngredientsById());
        recipeIngredients.forEach(ingredient -> {
            IngredientDto ingredientDto = new IngredientDto();
            ingredientDto.setId(String.valueOf(ingredient.getId()));
            ingredientDto.setName(ingredient.getIngredient().getName());
            ingredientDto.setQuantity(ingredient.getQuantity());
            ingredientDto.setUnit(ingredient.getUnit().getUnitOfMeasure());
            ingredients.add(ingredientDto);
        });
        recipeDto.setIngredients(ingredients);

        List<PrepStepDto> prepSteps = new ArrayList<>();
        List<RecipePrepStep> recipePrepSteps = recipePrepStepRepository.findAllByRecipeId(Long.valueOf(id));
        Collections.sort(recipePrepSteps, new SortPrepStepsById());
        recipePrepSteps.forEach(recipePrepStep -> {
            PrepStepDto PrepStepDto = new PrepStepDto();
            PrepStepDto.setId(String.valueOf(recipePrepStep.getRecipePrepStepId()));
            PrepStepDto.setPrepStep(recipePrepStep.getPrepStepName());
            prepSteps.add(PrepStepDto);
        });
        recipeDto.setPrepSteps(prepSteps);
        System.out.println(recipeDto.getImageUrl());
        return ResponseEntity.ok(recipeDto);
    }


    @CrossOrigin(origins = "*")
    @GetMapping("/getRecipeNames")
    public ResponseEntity<?> getRecipeNames(@RequestParam String name) {
        List<RecipeNameDto> recipeNameDtos = new ArrayList<>();
        if (name == null || name.isEmpty()) {
            return ResponseEntity.ok(recipeNameDtos);
        }
        recipeRepository.getNames(name).forEach(recipe -> {
            RecipeNameDto recipeNameDto = new RecipeNameDto();
            recipeNameDto.setId(recipe.getRecipeId());
            recipeNameDto.setName(recipe.getName());
            recipeNameDtos.add(recipeNameDto);
        });
        return ResponseEntity.ok(recipeNameDtos);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getRecipeNamesByParams")
    public ResponseEntity<?> getRecipeNamesByParams (@RequestParam String category, @RequestParam String subcategory,
                                            @RequestParam String type, @RequestParam String cuisine,
                                            @RequestParam String prepMethod, @RequestParam String ingredient) {
        if (category.isEmpty()) category = null;
        if (subcategory.isEmpty()) subcategory = null;
        if (type.isEmpty()) type = null;
        if (cuisine.isEmpty()) cuisine = null;
        if (prepMethod.isEmpty()) prepMethod = null;
        if (ingredient.isEmpty()) ingredient = null;

        List<RecipeNameDto> names = new ArrayList<>();
        if (category == null &&  subcategory == null && type == null &&  prepMethod == null && ingredient == null && cuisine == null) {
            return ResponseEntity.ok(names);
        }
        if (subcategory == null) {
            System.out.println("subcategory is null");
        }
        System.out.println("category " + category);
        System.out.println("subcategory " + subcategory);
        System.out.println("type " + type);
        System.out.println("prepMethod " + prepMethod);
        System.out.println("ingredient " + ingredient);


        recipeRepository.findRecipeNames(ingredient, category, subcategory, type, prepMethod, cuisine).forEach(recipe -> {
            RecipeNameDto nameDto = new RecipeNameDto();
            nameDto.setId(recipe.getRecipeId());
            nameDto.setName(recipe.getName());
            names.add(nameDto);
        });
        System.out.println("names" + names);
        return ResponseEntity.ok(names);
    }



    @CrossOrigin(origins = "*")
    @GetMapping("/getCuisines")
    public ResponseEntity<?> getCuisines() {
        List<CuisineDto> cuisines = new ArrayList<>();
        cuisineRepository.findAll().forEach(cuisine -> {
            CuisineDto cuisineDto = new CuisineDto();
            cuisineDto.setCuisineName(cuisine.getName());
            cuisines.add(cuisineDto);
        });
        return ResponseEntity.ok(cuisines);
    }
}
