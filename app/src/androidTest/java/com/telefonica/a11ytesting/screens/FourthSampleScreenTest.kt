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
class FourthSampleScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun verifyScreenIsDisplayed() {
        loadFourthSampleScreen()

        // Use accessibility DSL for comprehensive screen verification
        composeTestRule.accessibility {
            elementChecks {
                // Verify main heading structure is properly loaded
                element(FourthSampleScreenTestTags.MAIN_HEADING)
                    .isHeading()
                    .hasText("Accessibility Heading Examples")

                // Verify section structure is loaded
                element(FourthSampleScreenTestTags.SECTION_HEADING)
                    .isHeading()
                    .hasText("Basic Heading Patterns")

                // Verify intro text is accessible and not marked as heading
                element(FourthSampleScreenTestTags.INTRO_TEXT)
                    .isNotHeading()
                    .hasTextContaining("demonstrates proper heading hierarchy")

                // Verify navigation elements are accessible
                element(FourthSampleScreenTestTags.BACK_BUTTON_IMAGE, unmerged = true)
                    .hasContentDescription("Back")
                    .isNotHeading()
            }
        }

        // Additional basic verification using standard Compose testing
        // (Keep this for backward compatibility and basic UI verification)
        composeTestRule.onNodeWithText("Fourth Sample - Headings").assertIsDisplayed()
    }

    @Test
    fun verifyMainHeadingStructure() {
        loadFourthSampleScreen()

        composeTestRule.accessibility {
            elementChecks {
                // Main page heading - should be marked as heading
                element(FourthSampleScreenTestTags.MAIN_HEADING)
                    .isHeading()
                    .hasText("Accessibility Heading Examples")

                // Intro text should NOT be a heading
                element(FourthSampleScreenTestTags.INTRO_TEXT)
                    .isNotHeading()
                    .hasTextContaining("demonstrates proper heading hierarchy")
            }
        }
    }

    @Test
    fun verifySectionHeadingHierarchy() {
        loadFourthSampleScreen()

        composeTestRule.accessibility {
            elementChecks {
                // Level 2 section headings
                element(FourthSampleScreenTestTags.SECTION_HEADING)
                    .isHeading()
                    .hasText("Basic Heading Patterns")

                element(FourthSampleScreenTestTags.INTERACTIVE_SECTION_HEADING)
                    .isHeading()
                    .hasText("Interactive Headings")

                element(FourthSampleScreenTestTags.NESTED_SECTION_HEADING)
                    .isHeading()
                    .hasText("Nested Content Structure")

                // Level 3 subsection headings
                element(FourthSampleScreenTestTags.SUBSECTION_HEADING)
                    .isHeading()
                    .hasText("Simple Text Headings")

                element(FourthSampleScreenTestTags.CARD_SECTION_HEADING)
                    .isHeading()
                    .hasText("Card Headings")

                element(FourthSampleScreenTestTags.CLICKABLE_SUBSECTION_HEADING)
                    .isHeading()
                    .hasText("Clickable Headers")

                element(FourthSampleScreenTestTags.ARTICLES_HEADING)
                    .isHeading()
                    .hasText("Articles")
            }
        }
    }

    @Test
    fun verifyCardContentStructure() {
        loadFourthSampleScreen()

        composeTestRule.accessibility {
            elementChecks {
                // Card heading should be marked as heading
                element(FourthSampleScreenTestTags.CARD_HEADING)
                    .isHeading()
                    .hasText("Product Information")

                // Card content should NOT be headings
                element(FourthSampleScreenTestTags.PRODUCT_NAME)
                    .isNotHeading()
                    .hasTextContaining("Premium Headphones")

                element(FourthSampleScreenTestTags.PRODUCT_PRICE)
                    .isNotHeading()
                    .hasTextContaining("$199.99")

                element(FourthSampleScreenTestTags.PRODUCT_RATING)
                    .isNotHeading()
                    .hasTextContaining("4.5/5")
            }
        }
    }

    @Test
    fun verifyArticleHeadingStructure() {
        loadFourthSampleScreen()

        composeTestRule.accessibility {
            elementChecks {
                // Level 4 article headings
                element(FourthSampleScreenTestTags.ARTICLE_TITLE)
                    .isHeading()
                    .hasText("How to Test Accessibility")

                element(FourthSampleScreenTestTags.SECOND_ARTICLE_TITLE)
                    .isHeading()
                    .hasText("Screen Reader Navigation")

                // Article content should NOT be headings
                element(FourthSampleScreenTestTags.ARTICLE_CONTENT)
                    .isNotHeading()
                    .hasTextContaining("comprehensive guide")

                element(FourthSampleScreenTestTags.SECOND_ARTICLE_CONTENT)
                    .isNotHeading()
                    .hasTextContaining("Understanding how users navigate")
            }
        }
    }

    @Test
    fun verifyBadHeadingExample() {
        loadFourthSampleScreen()

        composeTestRule.accessibility {
            elementChecks {
                // This element looks like a heading but should NOT be marked as one
                // This demonstrates what NOT to do
                element(FourthSampleScreenTestTags.MISSING_HEADING_SEMANTIC)
                    .isNotHeading()
                    .hasText("Article Title (Missing Heading Semantic)")

                // Description should also not be a heading
                element(FourthSampleScreenTestTags.BAD_EXAMPLE_DESCRIPTION)
                    .isNotHeading()
                    .hasTextContaining("lacks the proper heading semantic")
            }
        }
    }

    @Test
    fun verifyAllProperHeadingsHaveText() {
        loadFourthSampleScreen()

        composeTestRule.accessibility {
            elementChecks {
                // Verify all proper headings have meaningful text
                element(FourthSampleScreenTestTags.MAIN_HEADING)
                    .isHeading()
                    .hasTextContaining("Accessibility")

                element(FourthSampleScreenTestTags.SECTION_HEADING)
                    .isHeading()
                    .hasTextContaining("Basic")

                element(FourthSampleScreenTestTags.SUBSECTION_HEADING)
                    .isHeading()
                    .hasTextContaining("Simple Text")

                element(FourthSampleScreenTestTags.CARD_HEADING)
                    .isHeading()
                    .hasTextContaining("Product")

                element(FourthSampleScreenTestTags.INTERACTIVE_SECTION_HEADING)
                    .isHeading()
                    .hasTextContaining("Interactive")

                element(FourthSampleScreenTestTags.NESTED_SECTION_HEADING)
                    .isHeading()
                    .hasTextContaining("Nested")

                element(FourthSampleScreenTestTags.ARTICLES_HEADING)
                    .isHeading()
                    .hasTextContaining("Articles")

                element(FourthSampleScreenTestTags.ARTICLE_TITLE)
                    .isHeading()
                    .hasTextContaining("Test Accessibility")

                element(FourthSampleScreenTestTags.SECOND_ARTICLE_TITLE)
                    .isHeading()
                    .hasTextContaining("Screen Reader")
            }
        }
    }

    @Test
    fun verifyNonHeadingElementsAreNotHeadings() {
        loadFourthSampleScreen()

        composeTestRule.accessibility {
            elementChecks {
                // Verify descriptive text elements are not marked as headings
                element(FourthSampleScreenTestTags.INTRO_TEXT)
                    .isNotHeading()

                element(FourthSampleScreenTestTags.BASIC_DESCRIPTION)
                    .isNotHeading()

                element(FourthSampleScreenTestTags.BAD_EXAMPLE_DESCRIPTION)
                    .isNotHeading()

                element(FourthSampleScreenTestTags.ARTICLE_CONTENT)
                    .isNotHeading()

                element(FourthSampleScreenTestTags.SECOND_ARTICLE_CONTENT)
                    .isNotHeading()

                // Product details should not be headings
                element(FourthSampleScreenTestTags.PRODUCT_NAME)
                    .isNotHeading()

                element(FourthSampleScreenTestTags.PRODUCT_PRICE)
                    .isNotHeading()

                element(FourthSampleScreenTestTags.PRODUCT_RATING)
                    .isNotHeading()
            }
        }
    }

    @Test
    fun verifyButtonsAreNotHeadings() {
        loadFourthSampleScreen()

        // Wait for the screen to fully load
        composeTestRule.waitForIdle()

        composeTestRule.accessibility {
            elementChecks {
                // Verify buttons are not marked as headings using test tags
                element(FourthSampleScreenTestTags.SECTION_BUTTON)
                    .isNotHeading()

                element(FourthSampleScreenTestTags.FOOTER_BUTTON)
                    .isNotHeading()

                // Button text should not be marked as heading (when unmerged)
                element(FourthSampleScreenTestTags.BUTTON_TEXT, unmerged = true)
                    .isNotHeading()
            }
        }
    }

    @Test
    fun verifyHeadingTextContent() {
        loadFourthSampleScreen()

        composeTestRule.accessibility {
            elementChecks {
                // Test specific text content of headings
                element(FourthSampleScreenTestTags.MAIN_HEADING)
                    .hasText("Accessibility Heading Examples")

                element(FourthSampleScreenTestTags.SECTION_HEADING)
                    .hasText("Basic Heading Patterns")

                element(FourthSampleScreenTestTags.SUBSECTION_HEADING)
                    .hasText("Simple Text Headings")

                element(FourthSampleScreenTestTags.CARD_SECTION_HEADING)
                    .hasText("Card Headings")

                element(FourthSampleScreenTestTags.CARD_HEADING)
                    .hasText("Product Information")

                element(FourthSampleScreenTestTags.INTERACTIVE_SECTION_HEADING)
                    .hasText("Interactive Headings")

                element(FourthSampleScreenTestTags.CLICKABLE_SUBSECTION_HEADING)
                    .hasText("Clickable Headers")

                element(FourthSampleScreenTestTags.NESTED_SECTION_HEADING)
                    .hasText("Nested Content Structure")

                element(FourthSampleScreenTestTags.ARTICLES_HEADING)
                    .hasText("Articles")

                element(FourthSampleScreenTestTags.ARTICLE_TITLE)
                    .hasText("How to Test Accessibility")

                element(FourthSampleScreenTestTags.SECOND_ARTICLE_TITLE)
                    .hasText("Screen Reader Navigation")
            }
        }
    }

    @Test
    fun verifyButtonTextContent() {
        loadFourthSampleScreen()

        composeTestRule.accessibility {
            elementChecks {
                // Footer button - comprehensive accessibility verification
                element(FourthSampleScreenTestTags.FOOTER_BUTTON)
                    .hasTextContaining("Test Heading Navigation")
                    .isNotHeading()

                // Section button - verify text content and accessibility properties
                element(FourthSampleScreenTestTags.SECTION_BUTTON)
                    .hasTextContaining("Navigate to Section")
                    .isNotHeading()

                // Button text elements should not be marked as headings (when unmerged)
                element(FourthSampleScreenTestTags.BUTTON_TEXT, unmerged = true)
                    .isNotHeading()
                    .hasText("Navigate to Section")
            }
        }
    }

    @Test
    fun verifyBackButtonAccessibility() {
        loadFourthSampleScreen()

        composeTestRule.accessibility {
            elementChecks {
                // Back button should have proper content description
                element(FourthSampleScreenTestTags.BACK_BUTTON_IMAGE, unmerged = true)
                    .hasContentDescription("Back")
                    .isNotHeading()
            }
        }
    }

    private fun loadFourthSampleScreen() {
        // Navigate to "Fourth Example" button in the main screen
        composeTestRule.onNodeWithText("Fourth Example")
            .performClick()

        // Wait for navigation to complete and screen to load
        composeTestRule.waitForIdle()

        // Verify we're on the correct screen
        composeTestRule.onNodeWithText("Fourth Sample - Headings").assertIsDisplayed()

        // Additional verification that key accessibility elements are loaded
        composeTestRule.waitForIdle()
    }
}
