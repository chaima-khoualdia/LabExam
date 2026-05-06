package com.example.chaimakhoualdia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chaimakhoualdia.ui.category.CategorySelectionScreen
import com.example.chaimakhoualdia.ui.difficulty.DifficultySettingsScreen
import com.example.chaimakhoualdia.ui.main.MainMenuScreen
import com.example.chaimakhoualdia.ui.quiz.QuizScreen
import com.example.chaimakhoualdia.ui.results.ResultsScreen
import com.example.chaimakhoualdia.ui.splash.SplashScreen
import com.example.chaimakhoualdia.viewmodel.QuizViewModel

object Routes {
    const val Splash = "splash"
    const val MainMenu = "main_menu"
    const val Category = "category"
    const val Difficulty = "difficulty"
    const val Quiz = "quiz"
    const val Results = "results"
}

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    viewModel: QuizViewModel,
    windowSizeClass: WindowSizeClass
) {
    val uiState = viewModel.uiState.collectAsState().value

    NavHost(navController = navController, startDestination = Routes.Splash) {
        composable(Routes.Splash) {
            SplashScreen(onTimeout = {
                navController.navigate(Routes.MainMenu) {
                    popUpTo(Routes.Splash) { inclusive = true }
                }
            })
        }
        composable(Routes.MainMenu) {
            MainMenuScreen(
                uiState = uiState,
                windowSizeClass = windowSizeClass.widthSizeClass,
                onStartQuiz = { navController.navigate(Routes.Category) }
            )
        }
        composable(Routes.Category) {
            CategorySelectionScreen(
                categories = uiState.categories,
                onCategorySelected = {
                    viewModel.selectCategory(it)
                    navController.navigate(Routes.Difficulty)
                }
            )
        }
        composable(Routes.Difficulty) {
            DifficultySettingsScreen(
                selectedCategory = uiState.selectedCategory,
                difficultyOptions = viewModel.difficultyOptions(),
                timerEnabled = uiState.timerEnabled,
                soundEnabled = uiState.soundEnabled,
                hapticEnabled = uiState.hapticEnabled,
                onDifficultySelected = viewModel::selectDifficulty,
                onTimerChange = { viewModel.setGameOptions(it, uiState.soundEnabled, uiState.hapticEnabled) },
                onSoundChange = { viewModel.setGameOptions(uiState.timerEnabled, it, uiState.hapticEnabled) },
                onHapticChange = { viewModel.setGameOptions(uiState.timerEnabled, uiState.soundEnabled, it) },
                onPlay = {
                    viewModel.startQuiz()
                    navController.navigate(Routes.Quiz)
                }
            )
        }
        composable(Routes.Quiz) {
            QuizScreen(
                state = uiState,
                onSelectAnswer = viewModel::selectAnswer,
                onSubmit = viewModel::submitAnswer,
                onNext = viewModel::nextQuestion,
                windowWidthSizeClass = windowSizeClass.widthSizeClass,
                onFinish = {
                    navController.navigate(Routes.Results) {
                        popUpTo(Routes.Quiz) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.Results) {
            ResultsScreen(
                uiState = uiState,
                onRetryQuiz = {
                    viewModel.retryQuiz()
                    navController.navigate(Routes.Category) {
                        popUpTo(Routes.Results) { inclusive = true }
                    }
                },
                onBackToMainMenu = {
                    viewModel.resetToMainMenu()
                    navController.navigate(Routes.MainMenu) {
                        popUpTo(Routes.Results) { inclusive = true }
                    }
                }
            )
        }
    }
}
