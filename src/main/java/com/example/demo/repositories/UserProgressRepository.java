package com.example.demo.repositories;

import com.example.demo.entities.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {
    // Findet heraus, ob der User diese Frage schonmal beantwortet hat
    Optional<UserProgress> findByUserIdAndQuestionId(Long userId, Long questionId);

    // Zählt die richtigen Antworten pro Kategorie für den Fortschrittsbalken
    long countByUserIdAndQuestionCategoryIdAndIsCorrectTrue(Long userId, Long categoryId);
}