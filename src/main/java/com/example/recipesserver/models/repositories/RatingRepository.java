package com.example.recipesserver.models.repositories;

import com.example.recipesserver.models.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating findByName(String name);
}
