package com.example.recipesserver.controllers;


import com.example.recipesserver.models.dto.*;
import com.example.recipesserver.models.entities.*;
import com.example.recipesserver.models.repositories.*;
import com.example.recipesserver.utilities.SortPrepStepsById;
import org.springframework.http.HttpStatus;
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
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping ("/addRecipe")
    public ResponseEntity<?> createRecipe(@RequestBody RecipeDto recipeDto) {
        if (recipeRepository.findByName(recipeDto.getName()) != null) {
            CreateRecipeDto createRecipeDto = new CreateRecipeDto();
            createRecipeDto.setMessage("Recipe already exists");
            return ResponseEntity.ok(createRecipeDto);
        }
        System.out.println(recipeDto);
        Recipe recipe = new Recipe();
        recipe.setName(recipeDto.getName());
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

    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/updateRecipe")
    public ResponseEntity<?> updateRecipe(@RequestBody RecipeDto recipeDto) {
        Recipe recipe = recipeRepository.findByName(recipeDto.getName());
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
        createRecipeDto.setMessage("Recipe updated successfully");
        return ResponseEntity.ok(createRecipeDto);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/getRecipe")
    public ResponseEntity<?> getRecipe(@RequestParam String name) {
        if (name == null || name.isEmpty() || recipeRepository.findByName(name) == null){
            return ResponseEntity.ok("");
        }
        Recipe recipe = recipeRepository.findByName(name);
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setName(recipe.getName());
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
        Set<RecipeIngredient> recipeIngredients = repository.findAllByRecipeName(name);
        recipeIngredients.forEach(ingredient -> {
            IngredientDto ingredientDto = new IngredientDto();
            ingredientDto.setName(ingredient.getIngredient().getName());
            ingredientDto.setQuantity(ingredient.getQuantity());
            ingredientDto.setUnit(ingredient.getUnit().getUnitOfMeasure());
            ingredients.add(ingredientDto);
        });
        recipeDto.setIngredients(ingredients);

        List<PrepStepDto> prepSteps = new ArrayList<>();
        List<RecipePrepStep> recipePrepSteps = recipePrepStepRepository.findAllByRecipeName(name);
        Collections.sort(recipePrepSteps, new SortPrepStepsById());
        recipePrepSteps.forEach(recipePrepStep -> {
            PrepStepDto PrepStepDto = new PrepStepDto();
            PrepStepDto.setPrepStep(recipePrepStep.getPrepStepName());
            prepSteps.add(PrepStepDto);
        });
        recipeDto.setPrepSteps(prepSteps);
        return ResponseEntity.ok(recipeDto);
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/getRecipeNames")
    public ResponseEntity<?> getRecipeNames() {
        List<RecipeNameDto> names = new ArrayList<>();
        recipeRepository.findAll().forEach(recipe -> {
            RecipeNameDto nameDto = new RecipeNameDto();
            nameDto.setName(recipe.getName());
            names.add(nameDto);
        });
        return ResponseEntity.ok(names);
    }

//    public void addRecipe(@RequestBody RecipeDto recipeDto) {
//        System.out.println(recipeDto.getName());
//        List<IngredientDto> ingredients = recipeDto.getIngredients();
//        ingredients.forEach(ingredient -> {
//            System.out.println(ingredient.getName());
//            System.out.println(ingredient.getUnit());
//            System.out.println(ingredient.getQuantity());
//        });
//    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/getRecipeNamesByCategory")
    public ResponseEntity<?> getRecipeNamesByCategory(@RequestParam String category) {
        List<RecipeNameDto> names = new ArrayList<>();
        Category cat = categoryRepository.findByName(category);
        recipeRepository.findAllByCategory(cat).forEach(recipe -> {
            RecipeNameDto nameDto = new RecipeNameDto();
            nameDto.setName(recipe.getName());
            names.add(nameDto);
        });
        return ResponseEntity.ok(names);
    }

    @CrossOrigin(origins = "http://localhost:5173")
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
