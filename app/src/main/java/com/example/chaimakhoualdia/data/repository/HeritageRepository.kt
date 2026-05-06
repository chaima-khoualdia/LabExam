package com.example.chaimakhoualdia.data.repository

import com.example.chaimakhoualdia.data.local.entity.Question
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * In-memory repository used as the single source of truth for quiz data.
 * The 12 Roman Heritage questions are seeded through [SeedData].
 */
class HeritageRepository {

    val categories: List<String> = SeedData.categoryCounts.keys.toList()
    val categoryCounts: Map<String, Int> = SeedData.categoryCounts

    fun questionsByCategory(category: String): Flow<List<Question>> =
        flowOf(SeedData.romanHeritageQuestions.filter { it.category == category })

    fun questionsByCategoryAndDifficulty(category: String, difficulty: String): Flow<List<Question>> =
        flowOf(
            SeedData.romanHeritageQuestions.filter {
                it.category == category && it.difficulty.equals(difficulty, ignoreCase = true)
            }
        )

    fun totalQuestionsCount(): Int = categoryCounts.values.sum()

    fun romanQuestions(): List<Question> = SeedData.romanHeritageQuestions
}



