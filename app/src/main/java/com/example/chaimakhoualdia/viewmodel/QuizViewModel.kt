package com.example.chaimakhoualdia.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chaimakhoualdia.data.local.entity.Question
import com.example.chaimakhoualdia.data.repository.HeritageRepository
import com.example.chaimakhoualdia.data.repository.SeedData
import com.example.chaimakhoualdia.ui.quiz.CategoryCardModel
import com.example.chaimakhoualdia.ui.quiz.DifficultyOptionModel
import com.example.chaimakhoualdia.ui.quiz.FeedbackState
import com.example.chaimakhoualdia.ui.quiz.QuizUiState
import com.example.chaimakhoualdia.util.ScoringUtil
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for the whole quiz flow.
 * It acts as the UDF state owner: UI sends intents, the ViewModel emits state.
 * The timer is intentionally handled here so the 15/20 second game rules stay consistent
 * no matter which screen is visible.
 */
class QuizViewModel : ViewModel() {

    private val repository = HeritageRepository()
    private var timerJob: Job? = null

    companion object {
        private const val TAG = "QuizViewModel"
        const val ANSWER_POINTS = 10
    }

    private val difficultyPresets = listOf(
        DifficultyOptionModel("Easy", "Famous landmarks", 15, true, "⭐"),
        DifficultyOptionModel("Medium", "Historical sites", 20, true, "⚡"),
        DifficultyOptionModel("Hard", "Archaeological details", 15, false, "🔥")
    )

    private val _uiState = MutableStateFlow(
        QuizUiState(
            categories = repository.categories.map { name ->
                CategoryCardModel(
                    name = name,
                    questionCount = repository.categoryCounts[name] ?: 0,
                    iconLabel = when (name) {
                        SeedData.ROMAN_HERITAGE -> "🏛️"
                        SeedData.ISLAMIC_HERITAGE -> "🕌"
                        SeedData.PUNIC_PRE_ROMAN -> "⚓"
                        SeedData.MODERN_HERITAGE -> "🏙️"
                        SeedData.NATURAL_MIXED_SITES -> "🌿"
                        SeedData.CITIES -> "🗺️"
                        else -> "🏺"
                    }
                )
            },
            totalSites = repository.totalQuestionsCount(),
            masteryPercent = 0,
            streak = 0
        )
    )
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    fun categoryCounts(): Map<String, Int> = repository.categoryCounts

    fun difficultyOptions(): List<DifficultyOptionModel> = difficultyPresets

    fun selectCategory(category: String) {
        _uiState.update { it.copy(selectedCategory = category, selectedDifficulty = difficultyPresets.first()) }
    }

    fun selectDifficulty(option: DifficultyOptionModel) {
        _uiState.update { it.copy(selectedDifficulty = option, timeLeftSec = option.secondsPerQuestion) }
    }

    fun setGameOptions(timerEnabled: Boolean, soundEnabled: Boolean, hapticEnabled: Boolean) {
        _uiState.update { it.copy(timerEnabled = timerEnabled, soundEnabled = soundEnabled, hapticEnabled = hapticEnabled) }
    }

    fun startQuiz() {
        val category = _uiState.value.selectedCategory ?: return
        val difficulty = _uiState.value.selectedDifficulty ?: difficultyPresets.first()

        viewModelScope.launch {
            val questions = repository.questionsByCategoryAndDifficulty(category, difficulty.title).first()
                .ifEmpty { repository.questionsByCategory(category).first() }

            _uiState.update {
                it.copy(
                    questions = questions,
                    currentQuestionIndex = 0,
                    selectedAnswer = null,
                    score = 0,
                    pointsThisQuestion = 0,
                    timeLeftSec = difficulty.secondsPerQuestion,
                    showFeedback = false,
                    feedback = null,
                    showResults = false,
                    finalPercentage = 0,
                    masteryMessage = ""
                )
            }
            startTimer()
        }
    }

    fun selectAnswer(answer: String) {
        _uiState.update { it.copy(selectedAnswer = answer) }
    }

    fun submitAnswer() {
        val state = _uiState.value
        val question = state.questions.getOrNull(state.currentQuestionIndex) ?: return
        val selected = state.selectedAnswer
        val correct = selected == question.correctAnswer
        val awarded = if (correct) ANSWER_POINTS else 0
        val feedback = if (correct) {
            FeedbackState(
                isCorrect = true,
                pointsAwarded = awarded,
                correctAnswer = question.correctAnswer,
                fact = question.fact,
                message = "Correct! +$ANSWER_POINTS points"
            )
        } else {
            FeedbackState(
                isCorrect = false,
                pointsAwarded = 0,
                correctAnswer = question.correctAnswer,
                fact = question.fact,
                message = "Incorrect. The correct answer is ${question.correctAnswer}."
            )
        }

        timerJob?.cancel()
        _uiState.update {
            it.copy(
                score = it.score + awarded,
                pointsThisQuestion = awarded,
                showFeedback = true,
                feedback = feedback,
                masteryPercent = ScoringUtil.calculatePercentage(it.score + awarded, totalMaxScore(it.questions.size))
            )
        }
    }

    fun nextQuestion() {
        val state = _uiState.value
        val nextIndex = state.currentQuestionIndex + 1
        val difficulty = state.selectedDifficulty ?: difficultyPresets.first()

        if (nextIndex >= state.questions.size) {
            finishQuiz()
        } else {
            _uiState.update {
                it.copy(
                    currentQuestionIndex = nextIndex,
                    selectedAnswer = null,
                    pointsThisQuestion = 0,
                    showFeedback = false,
                    feedback = null,
                    timeLeftSec = difficulty.secondsPerQuestion
                )
            }
            startTimer()
        }
    }

    fun retryQuiz() {
        timerJob?.cancel()
        _uiState.update {
            it.copy(
                currentQuestionIndex = 0,
                selectedAnswer = null,
                score = 0,
                pointsThisQuestion = 0,
                showFeedback = false,
                feedback = null,
                showResults = false,
                finalPercentage = 0,
                masteryMessage = ""
            )
        }
    }

    fun resetToMainMenu() {
        timerJob?.cancel()
        _uiState.update {
            it.copy(
                selectedCategory = null,
                selectedDifficulty = null,
                questions = emptyList(),
                currentQuestionIndex = 0,
                selectedAnswer = null,
                score = 0,
                pointsThisQuestion = 0,
                timeLeftSec = 0,
                showFeedback = false,
                feedback = null,
                showResults = false,
                finalPercentage = 0,
                masteryMessage = ""
            )
        }
    }

    fun loadSampleQuestionsForTest(questions: List<Question>, difficultySeconds: Int = 15) {
        _uiState.update {
            it.copy(
                selectedDifficulty = DifficultyOptionModel("Test", "Test", difficultySeconds, true, "⭐"),
                questions = questions,
                currentQuestionIndex = 0,
                selectedAnswer = null,
                score = 0,
                pointsThisQuestion = 0,
                timeLeftSec = difficultySeconds,
                showFeedback = false,
                feedback = null,
                showResults = false,
                finalPercentage = 0,
                masteryMessage = ""
            )
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        if (!_uiState.value.timerEnabled) return

        timerJob = viewModelScope.launch {
            while (_uiState.value.timeLeftSec > 0 && !_uiState.value.showFeedback && !_uiState.value.showResults) {
                delay(1000)
                _uiState.update { it.copy(timeLeftSec = it.timeLeftSec - 1) }
            }
            if (_uiState.value.timeLeftSec <= 0 && !_uiState.value.showFeedback && !_uiState.value.showResults) {
                Log.d(TAG, "Timer expired for question ${_uiState.value.currentQuestionIndex}")
                submitAnswer()
            }
        }
    }

    private fun finishQuiz() {
        val state = _uiState.value
        val maxScore = totalMaxScore(state.questions.size)
        val percent = ScoringUtil.calculatePercentage(state.score, maxScore)
        val masteryMessage = when {
            percent >= 90 -> "Master Explorer"
            percent >= 70 -> "Heritage Guardian"
            percent >= 50 -> "Promising Historian"
            else -> "Keep Exploring"
        }

        _uiState.update {
            it.copy(
                showResults = true,
                finalPercentage = percent,
                masteryMessage = masteryMessage,
                masteryPercent = percent,
                streak = state.score / ANSWER_POINTS
            )
        }
    }

    private fun totalMaxScore(questionCount: Int): Int = questionCount * ANSWER_POINTS

    override fun onCleared() {
        timerJob?.cancel()
        super.onCleared()
    }
}








