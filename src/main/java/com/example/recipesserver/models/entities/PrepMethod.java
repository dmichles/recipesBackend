package com.example.recipesserver.models.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "prep_method")
public class PrepMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prep_id")
    private Long prepId;

    private String name;

    @OneToMany
    private Set<Recipe> recipes = new HashSet<>();

    public PrepMethod() {}

    public Long getPrepId() {
        return prepId;
    }

    public void setId(Long prepId) {
        this.prepId = prepId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
