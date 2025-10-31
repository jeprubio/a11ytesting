package com.telefonica.a11ytesting.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.telefonica.a11ytesting.R

@Composable
fun FourthSampleScreen(
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            Heading(onBackClick)
        },
        bottomBar = {
            Footer()
        }
    ) { paddingValues ->
        Body(paddingValues)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Heading(onBackClick: () -> Unit) {
    TopAppBar(
        title = { Text("Fourth Sample - Headings") },
        navigationIcon = {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.testTag(FourthSampleScreenTestTags.BACK_BUTTON),
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back_24dp),
                    contentDescription = stringResource(R.string.back_button_accessibility_description),
                    modifier = Modifier.testTag(FourthSampleScreenTestTags.BACK_BUTTON_IMAGE),
                )
            }
        }
    )
}

@Composable
private fun Body(paddingValues: PaddingValues) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Main page heading - Level 1
        Text(
            text = "Accessibility Heading Examples",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .testTag(FourthSampleScreenTestTags.MAIN_HEADING)
                .semantics { heading() }
        )

        Text(
            text = "This screen demonstrates proper heading hierarchy for screen readers and accessibility testing.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.testTag(FourthSampleScreenTestTags.INTRO_TEXT)
        )

        // Section heading - Level 2
        Text(
            text = "Basic Heading Patterns",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .testTag(FourthSampleScreenTestTags.SECTION_HEADING)
                .semantics { heading() }
        )

        // Subsection heading - Level 3
        Text(
            text = "Simple Text Headings",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .testTag(FourthSampleScreenTestTags.SUBSECTION_HEADING)
                .semantics { heading() }
        )

        Text(
            text = "These are basic text elements marked as headings using the heading() semantic.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.testTag(FourthSampleScreenTestTags.BASIC_DESCRIPTION)
        )

        // Another subsection - Level 3
        Text(
            text = "Card Headings",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .testTag(FourthSampleScreenTestTags.CARD_SECTION_HEADING)
                .semantics { heading() }
        )

        // Example card with heading
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag(FourthSampleScreenTestTags.EXAMPLE_CARD)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Product Information",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .testTag(FourthSampleScreenTestTags.CARD_HEADING)
                        .semantics { heading() }
                )

                Text(
                    text = "Product Name: Premium Headphones",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.testTag(FourthSampleScreenTestTags.PRODUCT_NAME)
                )

                Text(
                    text = "Price: $199.99",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.testTag(FourthSampleScreenTestTags.PRODUCT_PRICE)
                )

                Text(
                    text = "Rating: 4.5/5 stars",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.testTag(FourthSampleScreenTestTags.PRODUCT_RATING)
                )
            }
        }

        HorizontalDivider(modifier = Modifier.testTag(FourthSampleScreenTestTags.DIVIDER))

        // Another major section - Level 2
        Text(
            text = "Interactive Headings",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .testTag(FourthSampleScreenTestTags.INTERACTIVE_SECTION_HEADING)
                .semantics { heading() }
        )

        // Subsection - Level 3
        Text(
            text = "Clickable Headers",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .testTag(FourthSampleScreenTestTags.CLICKABLE_SUBSECTION_HEADING)
                .semantics { heading() }
        )

        // Example of heading without proper semantic (bad example)
        Text(
            text = "Article Title (Missing Heading Semantic)",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.testTag(FourthSampleScreenTestTags.MISSING_HEADING_SEMANTIC)
            // Note: This is intentionally missing .semantics { heading() } to demonstrate bad practice
        )

        Text(
            text = "This text looks like a heading but lacks the proper heading semantic, making it inaccessible to screen readers navigating by headings.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.testTag(FourthSampleScreenTestTags.BAD_EXAMPLE_DESCRIPTION)
        )

        // Example of proper heading with interaction
        Button(
            onClick = {
                Toast.makeText(context, "Section button clicked!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag(FourthSampleScreenTestTags.SECTION_BUTTON)
        ) {
            Text(
                text = "Navigate to Section",
                modifier = Modifier
                    .testTag(FourthSampleScreenTestTags.BUTTON_TEXT)
            )
        }

        // Final section - Level 2
        Text(
            text = "Nested Content Structure",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .testTag(FourthSampleScreenTestTags.NESTED_SECTION_HEADING)
                .semantics { heading() }
        )

        // Nested subsections - Level 3
        Text(
            text = "Articles",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .testTag(FourthSampleScreenTestTags.ARTICLES_HEADING)
                .semantics { heading() }
        )

        // Individual article - Level 4 (using titleMedium for smaller heading)
        Text(
            text = "How to Test Accessibility",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .testTag(FourthSampleScreenTestTags.ARTICLE_TITLE)
                .semantics { heading() }
        )

        Text(
            text = "A comprehensive guide to testing accessibility in mobile applications...",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.testTag(FourthSampleScreenTestTags.ARTICLE_CONTENT)
        )

        // Another article - Level 4
        Text(
            text = "Screen Reader Navigation",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .testTag(FourthSampleScreenTestTags.SECOND_ARTICLE_TITLE)
                .semantics { heading() }
        )

        Text(
            text = "Understanding how users navigate content using screen readers and heading structures...",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.testTag(FourthSampleScreenTestTags.SECOND_ARTICLE_CONTENT)
        )
    }
}

@Composable
private fun Footer() {
    val context = LocalContext.current
    BottomAppBar {
        Button(
            onClick = {
                Toast.makeText(context, "Footer action clicked!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag(FourthSampleScreenTestTags.FOOTER_BUTTON)
                .padding(horizontal = 16.dp)
        ) {
            Text("Test Heading Navigation")
        }
    }
}

object FourthSampleScreenTestTags {
    const val BACK_BUTTON = "fourth_sample_screen_back_button"
    const val BACK_BUTTON_IMAGE = "fourth_sample_screen_back_button_image"

    // Main headings and structure
    const val MAIN_HEADING = "fourth_sample_screen_main_heading"
    const val INTRO_TEXT = "fourth_sample_screen_intro_text"
    const val SECTION_HEADING = "fourth_sample_screen_section_heading"
    const val SUBSECTION_HEADING = "fourth_sample_screen_subsection_heading"
    const val BASIC_DESCRIPTION = "fourth_sample_screen_basic_description"

    // Card example
    const val CARD_SECTION_HEADING = "fourth_sample_screen_card_section_heading"
    const val EXAMPLE_CARD = "fourth_sample_screen_example_card"
    const val CARD_HEADING = "fourth_sample_screen_card_heading"
    const val PRODUCT_NAME = "fourth_sample_screen_product_name"
    const val PRODUCT_PRICE = "fourth_sample_screen_product_price"
    const val PRODUCT_RATING = "fourth_sample_screen_product_rating"

    // Interactive section
    const val DIVIDER = "fourth_sample_screen_divider"
    const val INTERACTIVE_SECTION_HEADING = "fourth_sample_screen_interactive_section_heading"
    const val CLICKABLE_SUBSECTION_HEADING = "fourth_sample_screen_clickable_subsection_heading"

    // Bad example (missing heading semantic)
    const val MISSING_HEADING_SEMANTIC = "fourth_sample_screen_missing_heading_semantic"
    const val BAD_EXAMPLE_DESCRIPTION = "fourth_sample_screen_bad_example_description"

    // Button
    const val SECTION_BUTTON = "fourth_sample_screen_section_button"
    const val BUTTON_TEXT = "fourth_sample_screen_button_text"

    // Nested structure
    const val NESTED_SECTION_HEADING = "fourth_sample_screen_nested_section_heading"
    const val ARTICLES_HEADING = "fourth_sample_screen_articles_heading"
    const val ARTICLE_TITLE = "fourth_sample_screen_article_title"
    const val ARTICLE_CONTENT = "fourth_sample_screen_article_content"
    const val SECOND_ARTICLE_TITLE = "fourth_sample_screen_second_article_title"
    const val SECOND_ARTICLE_CONTENT = "fourth_sample_screen_second_article_content"

    const val FOOTER_BUTTON = "fourth_sample_screen_footer_button"
}
