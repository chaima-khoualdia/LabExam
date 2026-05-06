package com.example.chaimakhoualdia.util

/**
 * Small utility for scoring calculations. Kept separate to make unit testing trivial.
 */
object ScoringUtil {
    fun calculatePercentage(score: Int, total: Int): Int {
        if (total <= 0) return 0
        return ((score.toDouble() / total.toDouble()) * 100).toInt()
    }
}

