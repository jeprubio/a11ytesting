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
class SecondSampleScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun checkKeyboard() {
        loadSecondSampleScreen()

        composeTestRule.onNodeWithText("Second Sample").assertIsDisplayed()

        composeTestRule.accessibility {
            elementChecks {
                element(
                    identifier = SecondSampleScreenTestTags.BACK_BUTTON_IMAGE,
                    unmerged = true,
                ).hasContentDescription("Back")
            }

            keyboardFocus {
                prepareFocus()
                assertFocusOn(SecondSampleScreenTestTags.BACK_BUTTON)
                next() shouldFocus SecondSampleScreenTestTags.BODY_BUTTON
                next() shouldFocus SecondSampleScreenTestTags.BOTTOM_BUTTON
                next() shouldFocus SecondSampleScreenTestTags.BACK_BUTTON
            }
        }
    }

    @Test
    fun checkKeyboardBackwards() {
        loadSecondSampleScreen()

        composeTestRule.accessibility {

            keyboardFocus {
                prepareFocus()
                assertFocusOn(SecondSampleScreenTestTags.BACK_BUTTON)
                previous() shouldFocus SecondSampleScreenTestTags.BOTTOM_BUTTON
                previous() shouldFocus SecondSampleScreenTestTags.BODY_BUTTON
                previous() shouldFocus SecondSampleScreenTestTags.BACK_BUTTON
            }
        }
    }

    private fun loadSecondSampleScreen() {
        composeTestRule.onNodeWithText("Second Example")
            .performClick()
    }
}