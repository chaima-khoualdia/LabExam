package com.example.chaimakhoualdia

import com.example.chaimakhoualdia.data.local.entity.Question
import com.example.chaimakhoualdia.viewmodel.QuizViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class QuizViewModelTest {

    @Test
    fun selecting_correct_answer_updates_state_and_score() {
        val vm = QuizViewModel()
        val q = Question(
            id = 999,
            category = "Roman Heritage",
            monument = "Test",
            location = "",
            fact = "Test fact",
            imageRes = 0,
            options = listOf("A", "B", "C", "D"),
            correctAnswer = "A",
            difficulty = "Easy",
            hint = null
        )

        vm.loadSampleQuestionsForTest(listOf(q))
        vm.selectAnswer("A")
        vm.submitAnswer()

        val state = vm.uiState.value
        assertEquals("A", state.selectedAnswer)
        assertEquals(10, state.score)
        assertEquals(true, state.showFeedback)
        assertEquals(100, state.finalPercentage)
    }
}



