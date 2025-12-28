package com.example.recipesserver.models.repositories;

import com.example.recipesserver.models.entities.PrepMethod;
import jakarta.persistence.JoinColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrepMethodRepository extends JpaRepository<PrepMethod, Long> {
    PrepMethod findByName(String name);
}
