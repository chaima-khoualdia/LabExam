package com.example.chaimakhoualdia.data.local.entity

/**
 * Plain Kotlin data class representing a quiz question. This is not tied to Room in the
 * in-memory implementation so it remains a simple, testable model.
 */
data class Question(
    val id: Int,
    val category: String,
    val monument: String,
    val location: String,
    val fact: String,
    val imageRes: Int,
    val options: List<String>,
    val correctAnswer: String,
    val difficulty: String,
    val hint: String?
)


