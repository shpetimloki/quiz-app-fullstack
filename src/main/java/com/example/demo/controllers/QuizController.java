package com.example.demo.controllers;

import com.example.demo.entities.Question;
import com.example.demo.entities.UserProgress;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.repositories.UserProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") 
public class QuizController {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserProgressRepository progressRepository;

    // 1. GET /api/categories 
    @GetMapping("/categories")
    public List<Map<String, Object>> getCategories() {
        return categoryRepository.findAll().stream().map(cat -> {
            // Zähle alle Fragen in dieser Kategorie
            long total = questionRepository.findAll().stream()
                    .filter(q -> q.getCategory().getId().equals(cat.getId())).count();
            
            // Zähle richtige Antworten von User 1 in dieser Kategorie
            long correct = progressRepository.countByUserIdAndQuestionCategoryIdAndIsCorrectTrue(1L, cat.getId());
            
            Map<String, Object> data = new HashMap<>();
            data.put("id", cat.getId());
            data.put("name", cat.getName());
            data.put("correctCount", correct);
            data.put("totalCount", total);
            data.put("percent", total > 0 ? (correct * 100 / total) : 0);
            return data;
        }).toList();
    }

    // 2. GET /api/categories/{id}/next-question 
    @GetMapping("/categories/{id}/next-question")
    public ResponseEntity<?> getNextQuestion(@PathVariable Long id) {
        List<Question> questionsInCat = questionRepository.findAll().stream()
                .filter(q -> q.getCategory().getId().equals(id)).toList();

        for (Question q : questionsInCat) {
            // Prüfen, ob User 1 diese Frage schon richtig hat
            Optional<UserProgress> prog = progressRepository.findByUserIdAndQuestionId(1L, q.getId());
            if (prog.isEmpty() || !prog.get().isCorrect()) {
                return ResponseEntity.ok(q);
            }
        }
        // Falls alle gelöst sind:
        return ResponseEntity.ok(Collections.singletonMap("message", "Fertig! Alle Fragen gelöst."));
    }

    // 3. POST /api/questions/{id}/answer – Antwort prüfen & speichern
    @PostMapping("/questions/{id}/answer")
    public Map<String, Object> checkAnswer(@PathVariable Long id, @RequestBody Map<String, Integer> body) {
        Question question = questionRepository.findById(id).orElseThrow();
        int chosenIndex = body.get("answerIndex");
        boolean isCorrect = (chosenIndex == question.getCorrectAnswerIndex());

        // Fortschritt für User 1 speichern oder aktualisieren
        UserProgress progress = progressRepository.findByUserIdAndQuestionId(1L, id)
                .orElse(new UserProgress());
        
        progress.setQuestion(question);
        progress.setCorrect(isCorrect);
        progressRepository.save(progress);

        Map<String, Object> response = new HashMap<>();
        response.put("correct", isCorrect);
        response.put("correctIndex", question.getCorrectAnswerIndex());
        return response;
    }
}