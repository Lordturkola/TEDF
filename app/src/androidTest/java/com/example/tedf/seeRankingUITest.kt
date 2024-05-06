package com.example.tedf

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.tedf.ui.theme.TedfTheme
import org.junit.Rule
import org.junit.Test

class seeRankingUITest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun clickSeeRanking(){
        composeTestRule.setContent {
            TedfTheme {
                TedfApp()
            }
        }
        composeTestRule.onNodeWithText("Upload").performClick()

    }
}