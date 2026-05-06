package com.example.chaimakhoualdia

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.chaimakhoualdia.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationSplashToMenuTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun splash_navigates_to_main_menu() {
        composeTestRule.waitUntil(4000) {
            composeTestRule.onAllNodesWithText(composeTestRule.activity.getString(R.string.start_quiz_button)).fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.start_quiz_button)).assertExists()
    }
}



