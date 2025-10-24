package com.telefonica.a11ytesting.screens

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.telefonica.a11ytesting.MainActivity
import com.telefonica.a11ytesting.library.accessibility

@RunWith(AndroidJUnit4::class)
class FirstSampleScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun showSampleDetailScreen() {
        loadFirstSampleScreen()

        composeTestRule.onNodeWithText("First Sample").assertIsDisplayed()

        composeTestRule.accessibility {
            elementChecks {
                element(
                    identifier = FirstSampleScreenTestTags.BACK_BUTTON_IMAGE,
                    unmerged = true,
                ).hasContentDescription("Back")
            }

            keyboardFocus {
                assertFocusOn(FirstSampleScreenTestTags.BACK_BUTTON)
                next() shouldFocus FirstSampleScreenTestTags.BOTTOM_BUTTON
                next() shouldFocus FirstSampleScreenTestTags.BODY_BUTTON
                next() shouldFocus FirstSampleScreenTestTags.BACK_BUTTON
            }
        }
    }

    @Test
    fun checkKeyboardBackwards() {
        loadFirstSampleScreen()

        composeTestRule.accessibility {

            keyboardFocus {
                assertFocusOn(FirstSampleScreenTestTags.BACK_BUTTON)
                previous() shouldFocus FirstSampleScreenTestTags.BODY_BUTTON
                previous() shouldFocus FirstSampleScreenTestTags.BOTTOM_BUTTON
                previous() shouldFocus FirstSampleScreenTestTags.BACK_BUTTON
            }
        }
    }

    private fun loadFirstSampleScreen() {
        composeTestRule.onNodeWithText("First Example")
            .performClick()
    }
}
