package com.example.recipesserver.models.repositories;

import com.example.recipesserver.models.entities.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Long> {
    Cuisine findByName(String name);
    List<Cuisine> findAll();
}
