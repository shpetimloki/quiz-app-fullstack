package com.example.demo.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String text;
    
    @ElementCollection
    private List<String> options; 
    
    private int correctAnswerIndex;
    
    @ManyToOne
    private Category category;


    public Question() {}

    public Question(String text, List<String> options, int correctAnswerIndex, Category category) {
        this.text = text;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.category = category;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }
    public int getCorrectAnswerIndex() { return correctAnswerIndex; }
    public void setCorrectAnswerIndex(int correctAnswerIndex) { this.correctAnswerIndex = correctAnswerIndex; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}