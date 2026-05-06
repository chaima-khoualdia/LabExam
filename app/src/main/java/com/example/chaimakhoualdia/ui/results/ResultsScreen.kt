package com.example.chaimakhoualdia.ui.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.chaimakhoualdia.R
import com.example.chaimakhoualdia.ui.quiz.QuizUiState

@Composable
fun ResultsScreen(
    uiState: QuizUiState,
    onRetryQuiz: () -> Unit,
    onBackToMainMenu: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = stringResource(id = R.string.results_title), style = MaterialTheme.typography.headlineMedium)
                Text(text = stringResource(id = R.string.final_score_value, uiState.score))
                Text(text = stringResource(id = R.string.final_percentage_value, uiState.finalPercentage))
                Text(text = uiState.masteryMessage, style = MaterialTheme.typography.titleLarge)
            }
        }
        Button(onClick = onRetryQuiz, modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
            Text(text = stringResource(id = R.string.retry_quiz_button))
        }
        Button(onClick = onBackToMainMenu, modifier = Modifier.fillMaxWidth().padding(top = 12.dp)) {
            Text(text = stringResource(id = R.string.back_to_menu_button))
        }
    }
}

