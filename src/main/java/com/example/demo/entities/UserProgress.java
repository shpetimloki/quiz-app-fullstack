package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
public class UserProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long userId = 1L; 
    
    @ManyToOne
    private Question question;
    
    private boolean isCorrect;


    public UserProgress() {}

    public UserProgress(Long userId, Question question, boolean isCorrect) {
        this.userId = userId;
        this.question = question;
        this.isCorrect = isCorrect;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }
    public boolean isCorrect() { return isCorrect; }
    public void setCorrect(boolean isCorrect) { this.isCorrect = isCorrect; }
}