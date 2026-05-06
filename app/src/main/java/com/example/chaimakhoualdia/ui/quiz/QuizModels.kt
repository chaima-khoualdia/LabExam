package com.example.chaimakhoualdia.ui.quiz

import com.example.chaimakhoualdia.data.local.entity.Question

/** Simple models used by the Compose quiz flow. */
data class CategoryCardModel(
    val name: String,
    val questionCount: Int,
    val iconLabel: String
)

data class DifficultyOptionModel(
    val title: String,
    val subtitle: String,
    val secondsPerQuestion: Int,
    val hintsAllowed: Boolean,
    val icon: String
)

data class FeedbackState(
    val isCorrect: Boolean,
    val pointsAwarded: Int,
    val correctAnswer: String,
    val fact: String,
    val message: String
)

data class QuizUiState(
    val categories: List<CategoryCardModel> = emptyList(),
    val selectedCategory: String? = null,
    val selectedDifficulty: DifficultyOptionModel? = null,
    val timerEnabled: Boolean = true,
    val soundEnabled: Boolean = true,
    val hapticEnabled: Boolean = true,
    val questions: List<Question> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val selectedAnswer: String? = null,
    val score: Int = 0,
    val pointsThisQuestion: Int = 0,
    val timeLeftSec: Int = 0,
    val showFeedback: Boolean = false,
    val feedback: FeedbackState? = null,
    val showResults: Boolean = false,
    val finalPercentage: Int = 0,
    val masteryMessage: String = "",
    val totalSites: Int = 0,
    val masteryPercent: Int = 0,
    val streak: Int = 0
)

