package com.example.recipesserver.models.repositories;

import com.example.recipesserver.models.entities.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
    Unit findByUnitOfMeasure(String name);
}
