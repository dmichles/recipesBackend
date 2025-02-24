package com.example.recipesserver.models.repositories;

import com.example.recipesserver.models.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findById(Long id);

    Recipe findByName(String name);

    List<Recipe> findAll();

    List<Recipe> findAllByCategoryAndSubcategory(Category category, Subcategory subcategory);

    @Query(value = "SELECT DISTINCT r.* FROM recipe r " +
            "INNER JOIN category c ON r.category_id = c.category_id "
            + "INNER JOIN subcategory s ON s.subcategory_id = r.subcategory_id "
            + "INNER JOIN type t ON t.type_id = r.type_id "
            + "INNER JOIN cuisine cu ON cu.cuisine_id = r.cuisine_id "
            + "INNER JOIN prep_method p on p.prep_id = r.prep_id "
            + "INNER JOIN recipe_ingredient ri ON ri.recipe_id = r.recipe_id "
            + " INNER JOIN ingredient i ON i.ingredient_id = ri.ingredient_id " +
            " WHERE (:ingredientName IS NULL OR i.ingredient_name =:ingredientName) "
            + "AND (:categoryName IS NULL OR c.name =:categoryName) "
            + "AND (:subcategoryName IS NULL OR s.name =:subcategoryName) "
            + "AND (:typeName IS NULL OR t.name =:typeName) "
            + "AND (:prepMethodName IS NULL OR p.name =:prepMethodName) "
            + "AND (:cuisineName IS NULL OR cu.name =:cuisineName)",
            nativeQuery = true)
    List<Recipe> findRecipeNames(@Param("ingredientName") String ingredientName,
                                 @Param("categoryName") String categoryName,
                                 @Param("subcategoryName") String subcategoryName,
                                 @Param("typeName") String typeName,
                                 @Param("prepMethodName") String prepMethodName,
                                 @Param("cuisineName") String cuisineName);

    @Query(value = "SELECT r.* FROM recipe r WHERE r.name LIKE :name%", nativeQuery = true)
    List<Recipe> getNames(@Param("name") String name);
}