package com.example.chaimakhoualdia.viewmodel

import com.example.chaimakhoualdia.data.local.entity.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for QuizViewModel.
 * Verifies state transitions, scoring, and quiz flow logic.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class QuizViewModelTest {

    private lateinit var viewModel: QuizViewModel
    private val testDispatcher = StandardTestDispatcher()

    private val sampleQuestion = Question(
        id = 1,
        category = "Roman Heritage",
        monument = "El Jem",
        location = "El Jem",
        fact = "Fact",
        imageRes = 0,
        options = listOf("El Jem", "Dougga"),
        correctAnswer = "El Jem",
        difficulty = "Easy",
        hint = "Hint"
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = QuizViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun startQuiz_initializesQuestionsCorrectly() = runTest {
        // Use a test helper to inject questions
        viewModel.loadSampleQuestionsForTest(listOf(sampleQuestion))
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(1, state.questions.size)
        assertEquals(0, state.currentQuestionIndex)
        assertEquals(0, state.score)
        assertNull(state.selectedAnswer)
        assertFalse(state.showFeedback)
    }

    @Test
    fun submitAnswer_correctAnswer_updatesScoreAndFeedback() = runTest {
        viewModel.loadSampleQuestionsForTest(listOf(sampleQuestion))
        advanceUntilIdle()

        viewModel.selectAnswer("El Jem")
        viewModel.submitAnswer()

        val state = viewModel.uiState.value
        assertEquals(QuizViewModel.ANSWER_POINTS, state.score)
        assertTrue(state.showFeedback)
        assertNotNull(state.feedback)
        assertTrue(state.feedback!!.isCorrect)
    }

    @Test
    fun submitAnswer_incorrectAnswer_updatesFeedbackOnly() = runTest {
        viewModel.loadSampleQuestionsForTest(listOf(sampleQuestion))
        advanceUntilIdle()

        viewModel.selectAnswer("Dougga")
        viewModel.submitAnswer()

        val state = viewModel.uiState.value
        assertEquals(0, state.score)
        assertTrue(state.showFeedback)
        assertNotNull(state.feedback)
        assertFalse(state.feedback!!.isCorrect)
    }

    @Test
    fun nextQuestion_advancesIndexAndResetsState() = runTest {
        val q2 = sampleQuestion.copy(id = 2, monument = "Dougga")
        viewModel.loadSampleQuestionsForTest(listOf(sampleQuestion, q2))
        advanceUntilIdle()

        viewModel.selectAnswer("El Jem")
        viewModel.submitAnswer()
        viewModel.nextQuestion()

        val state = viewModel.uiState.value
        assertEquals(1, state.currentQuestionIndex)
        assertNull(state.selectedAnswer)
        assertFalse(state.showFeedback)
        assertNull(state.feedback)
    }

    @Test
    fun retryQuiz_resetsState() = runTest {
        viewModel.loadSampleQuestionsForTest(listOf(sampleQuestion))
        advanceUntilIdle()

        viewModel.selectAnswer("El Jem")
        viewModel.submitAnswer()
        viewModel.retryQuiz()

        val state = viewModel.uiState.value
        assertEquals(0, state.currentQuestionIndex)
        assertEquals(0, state.score)
        assertNull(state.selectedAnswer)
        assertFalse(state.showFeedback)
    }

    @Test
    fun resetToMainMenu_clearsAllState() = runTest {
        viewModel.loadSampleQuestionsForTest(listOf(sampleQuestion))
        advanceUntilIdle()

        viewModel.resetToMainMenu()

        val state = viewModel.uiState.value
        assertTrue(state.questions.isEmpty())
        assertNull(state.selectedCategory)
        assertEquals(0, state.score)
    }
}
