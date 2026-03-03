package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Standard-Konstruktor (WICHTIG für JPA)
    public Category() {}

    // Manueller Getter für ID
    public Long getId() {
        return id;
    }

    // Manueller Setter für ID
    public void setId(Long id) {
        this.id = id;
    }

    // Manueller Getter für Name
    public String getName() {
        return name;
    }

    // Manueller Setter für Name
    public void setName(String name) {
        this.name = name;
    }
}