package com.example.chaimakhoualdia.ui.difficulty

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.chaimakhoualdia.R
import com.example.chaimakhoualdia.ui.quiz.DifficultyOptionModel

@Composable
fun DifficultySettingsScreen(
    selectedCategory: String?,
    difficultyOptions: List<DifficultyOptionModel>,
    timerEnabled: Boolean,
    soundEnabled: Boolean,
    hapticEnabled: Boolean,
    onDifficultySelected: (DifficultyOptionModel) -> Unit,
    onTimerChange: (Boolean) -> Unit,
    onSoundChange: (Boolean) -> Unit,
    onHapticChange: (Boolean) -> Unit,
    onPlay: () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = stringResource(id = R.string.settings_title), style = MaterialTheme.typography.headlineMedium)
        Text(text = stringResource(id = R.string.selected_category_label, selectedCategory.orEmpty()), style = MaterialTheme.typography.bodyLarge)

        difficultyOptions.forEach { option ->
            Card(onClick = { onDifficultySelected(option) }) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = option.icon + " " + option.title, style = MaterialTheme.typography.titleMedium)
                    Text(text = option.subtitle)
                    Text(text = stringResource(id = R.string.seconds_per_question, option.secondsPerQuestion))
                    Text(text = if (option.hintsAllowed) stringResource(id = R.string.hints_allowed) else stringResource(id = R.string.hints_disabled))
                }
            }
        }

        Text(text = stringResource(id = R.string.game_options_title), style = MaterialTheme.typography.titleLarge)
        OptionRow(label = stringResource(id = R.string.timer_label), checked = timerEnabled, onCheckedChange = onTimerChange)
        OptionRow(label = stringResource(id = R.string.sound_label), checked = soundEnabled, onCheckedChange = onSoundChange)
        OptionRow(label = stringResource(id = R.string.haptic_label), checked = hapticEnabled, onCheckedChange = onHapticChange)

        Spacer(modifier = Modifier.padding(top = 8.dp))
        Button(onClick = onPlay, modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(id = R.string.play_button))
        }
    }
}

@Composable
private fun OptionRow(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = label)
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}


