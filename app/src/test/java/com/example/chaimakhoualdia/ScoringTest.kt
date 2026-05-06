package com.example.chaimakhoualdia

import com.example.chaimakhoualdia.util.ScoringUtil
import org.junit.Assert.assertEquals
import org.junit.Test

class ScoringTest {
    @Test
    fun percentage_calculation_is_correct() {
        assertEquals(50, ScoringUtil.calculatePercentage(1, 2))
        assertEquals(0, ScoringUtil.calculatePercentage(0, 5))
        assertEquals(100, ScoringUtil.calculatePercentage(5, 5))
    }
}

