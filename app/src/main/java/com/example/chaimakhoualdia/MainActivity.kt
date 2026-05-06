package com.example.chaimakhoualdia

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import com.example.chaimakhoualdia.ui.main.MainMenuScreen
import com.example.chaimakhoualdia.ui.navigation.AppNavHost
import com.example.chaimakhoualdia.ui.quiz.QuizUiState
import com.example.chaimakhoualdia.ui.theme.ChaimaKhoualdiaTheme
import com.example.chaimakhoualdia.viewmodel.QuizViewModel

/**
 * MainActivity hosts the Compose NavHost. Lifecycle events are logged for the academic rubric.
 */
class MainActivity : ComponentActivity() {

    private val quizViewModel: QuizViewModel by viewModels()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "Stage: onCreate")
        enableEdgeToEdge()
        
        setContent {
            // calculateWindowSizeClass is a @Composable and MUST be called inside setContent
            val windowSizeClass = calculateWindowSizeClass(this)

            ChaimaKhoualdiaTheme {
                Surface {
                    AppNavHost(viewModel = quizViewModel, windowSizeClass = windowSizeClass)
                }
            }
        }
    }

    override fun onStart() { super.onStart(); Log.d("Lifecycle", "Stage: onStart") }
    override fun onResume() { super.onResume(); Log.d("Lifecycle", "Stage: onResume") }
    override fun onPause() { super.onPause(); Log.d("Lifecycle", "Stage: onPause") }
    override fun onStop() { super.onStop(); Log.d("Lifecycle", "Stage: onStop") }
    override fun onDestroy() { super.onDestroy(); Log.d("Lifecycle", "Stage: onDestroy") }
    override fun onRestart() { super.onRestart(); Log.d("Lifecycle", "Stage: onRestart") }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun MainMenuPreview() {
    ChaimaKhoualdiaTheme {
        Surface {
            MainMenuScreen(
                uiState = QuizUiState(
                    totalSites = 25,
                    masteryPercent = 85,
                    streak = 3
                ),
                windowSizeClass = WindowWidthSizeClass.Compact,
                onStartQuiz = {}
            )
        }
    }
}
