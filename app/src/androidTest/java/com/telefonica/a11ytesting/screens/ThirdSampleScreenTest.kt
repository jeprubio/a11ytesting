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
class ThirdSampleScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun verifyMergedContentDescriptions() {
        loadThirdSampleScreen()

        composeTestRule.onNodeWithText("Third Sample - Merged Content").assertIsDisplayed()

        composeTestRule.accessibility {
            elementChecks {
                // Test 1: Product card has merged content description
                element(ThirdSampleScreenTestTags.PRODUCT_CARD)
                    .hasContentDescription("Product: Premium Headphones, Price: \$199, 4.5 stars rating, Add to cart")
                    .hasMergedContentDescription()

                // Test 2: Verify individual elements within the card don't have content descriptions
                element(ThirdSampleScreenTestTags.PRODUCT_IMAGE, unmerged = true)
                    .hasNoContentDescription()

                element(ThirdSampleScreenTestTags.PRODUCT_TITLE, unmerged = true)
                    .hasNoContentDescription()

                element(ThirdSampleScreenTestTags.PRODUCT_PRICE, unmerged = true)
                    .hasNoContentDescription()

                element(ThirdSampleScreenTestTags.PRODUCT_RATING, unmerged = true)
                    .hasNoContentDescription()
            }
        }
    }

    @Test
    fun verifyScreenIsDisplayed() {
        loadThirdSampleScreen()

        // Basic verification that the screen loads correctly
        composeTestRule.onNodeWithText("Third Sample - Merged Content").assertIsDisplayed()
        composeTestRule.onNodeWithText("Examples of Merged Content Descriptions").assertIsDisplayed()
        composeTestRule.onNodeWithText("Premium Headphones").assertIsDisplayed()
    }

    @Test
    fun verifyButtonWithIconMerging() {
        loadThirdSampleScreen()

        composeTestRule.accessibility {
            elementChecks {
                // Share button merges icon and text naturally (text becomes accessible name)
                // Note: Buttons don't set ContentDescription, they use text for accessibility
                element(ThirdSampleScreenTestTags.SHARE_BUTTON)
                    .hasMergedContentDescription()

                // Individual elements should not have separate content descriptions
                element(ThirdSampleScreenTestTags.SHARE_ICON, unmerged = true)
                    .hasNoContentDescription()

                element(ThirdSampleScreenTestTags.SHARE_TEXT, unmerged = true)
                    .hasNoContentDescription()
            }
        }

        // Verify the button is accessible via text
        composeTestRule.onNodeWithText("Share this product").assertIsDisplayed()
    }

    @Test
    fun verifyComplexMergedContentWithClearAndSetSemantics() {
        loadThirdSampleScreen()

        composeTestRule.accessibility {
            elementChecks {
                // Article container should have the complete merged description
                element(ThirdSampleScreenTestTags.ARTICLE_CONTAINER)
                    .hasContentDescription("Article: How to test accessibility, Published 2 hours ago by John Doe, 5 minute read, Tap to open")
                    .hasMergedContentDescription()

                // All child elements should not have individual content descriptions
                // due to clearAndSetSemantics
                element(ThirdSampleScreenTestTags.ARTICLE_TITLE, unmerged = true)
                    .hasNoContentDescription()

                element(ThirdSampleScreenTestTags.ARTICLE_DATE, unmerged = true)
                    .hasNoContentDescription()

                element(ThirdSampleScreenTestTags.ARTICLE_AUTHOR, unmerged = true)
                    .hasNoContentDescription()

                element(ThirdSampleScreenTestTags.ARTICLE_READ_TIME, unmerged = true)
                    .hasNoContentDescription()
            }
        }
    }

    @Test
    fun verifyPartialContentDescriptionMatching() {
        loadThirdSampleScreen()

        composeTestRule.accessibility {
            elementChecks {
                // Test partial matching on product card
                element(ThirdSampleScreenTestTags.PRODUCT_CARD)
                    .hasContentDescriptionContaining("Premium Headphones")
                    .hasContentDescriptionContaining("$199")
                    .hasContentDescriptionContaining("4.5 stars")
                    .hasContentDescriptionContaining("Add to cart")

                // Test partial matching on article
                element(ThirdSampleScreenTestTags.ARTICLE_CONTAINER)
                    .hasContentDescriptionContaining("How to test accessibility")
                    .hasContentDescriptionContaining("John Doe")
                    .hasContentDescriptionContaining("5 minute read")
            }
        }
    }

    @Test
    fun verifyRegexPatternMatching() {
        loadThirdSampleScreen()

        composeTestRule.accessibility {
            elementChecks {
                // Test regex patterns for price format
                element(ThirdSampleScreenTestTags.PRODUCT_CARD)
                    .hasContentDescriptionMatching(Regex(".*\\$\\d+.*")) // Contains price pattern
                    .hasContentDescriptionMatching(Regex(".*\\d+\\.\\d+ stars.*")) // Contains rating pattern

                // Test regex for time pattern in article
                element(ThirdSampleScreenTestTags.ARTICLE_CONTAINER)
                    .hasContentDescriptionMatching(Regex(".*\\d+ hours? ago.*")) // Time pattern
                    .hasContentDescriptionMatching(Regex(".*\\d+ minute read.*")) // Read time pattern
            }
        }
    }

    @Test
    fun verifySeparateElementsAreNotMerged() {
        loadThirdSampleScreen()

        composeTestRule.accessibility {
            elementChecks {
                // The row container should not have a merged description
                element(ThirdSampleScreenTestTags.SEPARATE_BUTTONS_ROW, unmerged = true)
                    .hasNoContentDescription()
            }
        }

        // Verify the buttons are accessible via their text (not content descriptions)
        composeTestRule.onNodeWithText("Save").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cancel").assertIsDisplayed()
    }

    @Test
    fun verifyBackButtonAccessibility() {
        loadThirdSampleScreen()

        composeTestRule.accessibility {
            elementChecks {
                // Back button should have proper content description
                element(ThirdSampleScreenTestTags.BACK_BUTTON_IMAGE, unmerged = true)
                    .hasContentDescription("Back")
            }
        }
    }

    @Test
    fun verifyAllMergedElementsHaveProperContentDescriptions() {
        loadThirdSampleScreen()

        composeTestRule.accessibility {
            elementChecks {
                // Ensure elements with explicit content descriptions have them properly set
                element(ThirdSampleScreenTestTags.PRODUCT_CARD)
                    .hasAnyContentDescriptionDefined()
                    .hasNonBlankContentDescription()

                element(ThirdSampleScreenTestTags.ARTICLE_CONTAINER)
                    .hasAnyContentDescriptionDefined()
                    .hasNonBlankContentDescription()
            }
        }

        // Verify buttons are accessible via their text content
        composeTestRule.onNodeWithText("Share this product").assertIsDisplayed()
        composeTestRule.onNodeWithText("Save").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cancel").assertIsDisplayed()
        composeTestRule.onNodeWithText("Test Merged Content").assertIsDisplayed()
    }

    private fun loadThirdSampleScreen() {
        // Assuming there's a "Third Example" button in the main screen
        composeTestRule.onNodeWithText("Third Example")
            .performClick()
    }
}
