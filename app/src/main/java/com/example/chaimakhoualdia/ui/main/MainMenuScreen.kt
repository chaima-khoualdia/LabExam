package com.example.chaimakhoualdia.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import com.example.chaimakhoualdia.R
import com.example.chaimakhoualdia.ui.quiz.QuizUiState

@Composable
fun MainMenuScreen(
    uiState: QuizUiState,
    windowSizeClass: WindowWidthSizeClass,
    onStartQuiz: () -> Unit
) {
    val isExpanded = windowSizeClass == WindowWidthSizeClass.Expanded

    if (isExpanded) {
        Row(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
                Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = stringResource(id = R.string.main_menu_subtitle), style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = onStartQuiz) {
                    Text(text = stringResource(id = R.string.start_quiz_button))
                }
            }
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                StatsRow(uiState = uiState)
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(id = R.string.main_menu_subtitle), style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(20.dp))
            StatsRow(uiState = uiState)
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onStartQuiz, modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.start_quiz_button))
            }
        }
    }
}

@Composable
private fun StatsRow(uiState: QuizUiState) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
        StatCard(title = stringResource(id = R.string.sites_count_label), value = uiState.totalSites.toString(), modifier = Modifier.weight(1f))
        StatCard(title = stringResource(id = R.string.mastery_label), value = stringResource(id = R.string.percentage_value, uiState.masteryPercent), modifier = Modifier.weight(1f))
        StatCard(title = stringResource(id = R.string.streak_label), value = uiState.streak.toString(), modifier = Modifier.weight(1f))
    }
}

@Composable
private fun StatCard(title: String, value: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = title, style = MaterialTheme.typography.labelMedium)
            Text(text = value, style = MaterialTheme.typography.headlineSmall)
        }
    }
}




