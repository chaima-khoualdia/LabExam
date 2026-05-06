package com.example.chaimakhoualdia.ui.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import com.example.chaimakhoualdia.R
import com.example.chaimakhoualdia.data.local.entity.Question

@Composable
fun QuizScreen(
    state: QuizUiState,
    onSelectAnswer: (String) -> Unit,
    onSubmit: () -> Unit,
    onNext: () -> Unit,
    windowWidthSizeClass: WindowWidthSizeClass,
    onFinish: () -> Unit
) {
    LaunchedEffect(state.showResults) {
        if (state.showResults) onFinish()
    }

    val question = state.questions.getOrNull(state.currentQuestionIndex)
    if (question == null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(id = R.string.no_questions_long))
        }
        return
    }

    val isExpanded = windowWidthSizeClass == WindowWidthSizeClass.Expanded

    if (isExpanded) {
        Row(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            QuestionPanel(
                modifier = Modifier.weight(1f),
                question = question,
                state = state,
                onSubmit = onSubmit,
                onSelect = onSelectAnswer
            )
            SidePanel(
                modifier = Modifier.weight(1f),
                state = state,
                onNext = onNext
            )
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            QuestionPanel(
                modifier = Modifier.weight(1f),
                question = question,
                state = state,
                onSubmit = onSubmit,
                onSelect = onSelectAnswer
            )
            if (state.showFeedback) {
                SidePanel(
                    modifier = Modifier.fillMaxWidth(),
                    state = state,
                    onNext = onNext
                )
            }
        }
    }
}

@Composable
private fun QuestionPanel(
    modifier: Modifier,
    question: Question,
    state: QuizUiState,
    onSubmit: () -> Unit,
    onSelect: (String) -> Unit
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = stringResource(
                id = R.string.question_progress,
                state.currentQuestionIndex + 1,
                state.questions.size
            ),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(text = question.monument, style = MaterialTheme.typography.headlineSmall)

        // Using standard Image with painterResource as Coil is not currently in dependencies
        Image(
            painter = painterResource(id = question.imageRes),
            contentDescription = null,
            modifier = Modifier.size(240.dp)
        )

        Text(text = question.fact)
        Text(text = stringResource(id = R.string.time_left, state.timeLeftSec))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(question.options) { option ->
                Card(onClick = { onSelect(option) }) {
                    Text(text = option, modifier = Modifier.padding(16.dp))
                }
            }
        }

        Button(
            onClick = onSubmit,
            modifier = Modifier.fillMaxWidth(),
            enabled = state.selectedAnswer != null && !state.showFeedback
        ) {
            Text(text = stringResource(id = R.string.submit_button))
        }
    }
}

@Composable
private fun SidePanel(
    modifier: Modifier,
    state: QuizUiState,
    onNext: () -> Unit
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(text = stringResource(id = R.string.score, state.score, state.questions.size * 10))
        if (state.showFeedback) {
            Card {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = state.feedback?.message.orEmpty(),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = stringResource(
                            id = R.string.correct_answer_label,
                            state.feedback?.correctAnswer.orEmpty()
                        )
                    )
                    Text(
                        text = stringResource(
                            id = R.string.fact_label,
                            state.feedback?.fact.orEmpty()
                        )
                    )
                    Button(onClick = onNext, modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(id = R.string.next_button))
                    }
                }
            }
        }
    }
}
