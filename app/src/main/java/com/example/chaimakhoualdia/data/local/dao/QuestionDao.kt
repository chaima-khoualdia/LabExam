package com.example.chaimakhoualdia.data.local.dao

import com.example.chaimakhoualdia.data.local.entity.Question
import kotlinx.coroutines.flow.Flow

/**
 * DAO-like interface kept for API compatibility; the in-memory repository implements
 * equivalent functions without Room.
 */
interface QuestionDao {
    fun getQuestionsByCategory(category: String): Flow<List<Question>>
    fun getQuestionsByCategoryAndDifficulty(category: String, difficulty: String): Flow<List<Question>>
    suspend fun countByCategory(category: String): Int
    suspend fun insertAll(questions: List<Question>)
}


