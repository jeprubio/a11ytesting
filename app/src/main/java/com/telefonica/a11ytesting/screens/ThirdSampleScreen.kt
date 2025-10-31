package com.telefonica.a11ytesting.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.telefonica.a11ytesting.R

@Composable
fun ThirdSampleScreen(
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
        title = { Text("Third Sample - Merged Content") },
        navigationIcon = {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.testTag(ThirdSampleScreenTestTags.BACK_BUTTON),
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back_24dp),
                    contentDescription = stringResource(R.string.back_button_accessibility_description),
                    modifier = Modifier.testTag(ThirdSampleScreenTestTags.BACK_BUTTON_IMAGE),
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
        Text(
            text = "Examples of Merged Content Descriptions",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Example 1: Clickable card with merged content description
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    Toast.makeText(context, "Product card clicked!", Toast.LENGTH_SHORT).show()
                }
                .testTag(ThirdSampleScreenTestTags.PRODUCT_CARD)
                .semantics {
                    contentDescription = "Product: Premium Headphones, Price: $199, 4.5 stars rating, Add to cart"
                }
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.arrow_back_24dp), // Using placeholder icon
                    contentDescription = null, // This will be merged
                    modifier = Modifier
                        .size(64.dp)
                        .testTag(ThirdSampleScreenTestTags.PRODUCT_IMAGE)
                )
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "Premium Headphones",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.testTag(ThirdSampleScreenTestTags.PRODUCT_TITLE)
                    )
                    Text(
                        text = "$199",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.testTag(ThirdSampleScreenTestTags.PRODUCT_PRICE)
                    )
                    Text(
                        text = "★★★★☆ 4.5",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.testTag(ThirdSampleScreenTestTags.PRODUCT_RATING)
                    )
                }
            }
        }

        // Example 2: Button with icon and text - natural merging
        Button(
            onClick = {
                Toast.makeText(context, "Share button clicked!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag(ThirdSampleScreenTestTags.SHARE_BUTTON)
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_back_24dp), // Using placeholder
                contentDescription = null, // Will merge with button text
                modifier = Modifier.testTag(ThirdSampleScreenTestTags.SHARE_ICON)
            )
            Text(
                text = "Share this product",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .testTag(ThirdSampleScreenTestTags.SHARE_TEXT)
            )
        }

        // Example 3: Complex merged content with clearAndSetSemantics
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    Toast.makeText(context, "Article clicked!", Toast.LENGTH_SHORT).show()
                }
                .testTag(ThirdSampleScreenTestTags.ARTICLE_CONTAINER)
                .clearAndSetSemantics {
                    contentDescription = "Article: How to test accessibility, Published 2 hours ago by John Doe, 5 minute read, Tap to open"
                }
        ) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "How to test accessibility",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.testTag(ThirdSampleScreenTestTags.ARTICLE_TITLE)
                    )
                    Text(
                        text = "Published 2 hours ago",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.testTag(ThirdSampleScreenTestTags.ARTICLE_DATE)
                    )
                    Text(
                        text = "By John Doe",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.testTag(ThirdSampleScreenTestTags.ARTICLE_AUTHOR)
                    )
                    Text(
                        text = "5 min read",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.testTag(ThirdSampleScreenTestTags.ARTICLE_READ_TIME)
                    )
                }
            }
        }

        // Example 4: Row with separate elements that should NOT merge
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .testTag(ThirdSampleScreenTestTags.SEPARATE_BUTTONS_ROW),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    Toast.makeText(context, "Save clicked!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .weight(1f)
                    .testTag(ThirdSampleScreenTestTags.SAVE_BUTTON)
            ) {
                Text("Save")
            }
            Button(
                onClick = {
                    Toast.makeText(context, "Cancel clicked!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .weight(1f)
                    .testTag(ThirdSampleScreenTestTags.CANCEL_BUTTON)
            ) {
                Text("Cancel")
            }
        }
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
                .testTag(ThirdSampleScreenTestTags.FOOTER_BUTTON)
                .padding(horizontal = 16.dp)
        ) {
            Text("Test Merged Content")
        }
    }
}

object ThirdSampleScreenTestTags {
    const val BACK_BUTTON = "third_sample_screen_back_button"
    const val BACK_BUTTON_IMAGE = "third_sample_screen_back_button_image"

    // Product card examples
    const val PRODUCT_CARD = "third_sample_screen_product_card"
    const val PRODUCT_IMAGE = "third_sample_screen_product_image"
    const val PRODUCT_TITLE = "third_sample_screen_product_title"
    const val PRODUCT_PRICE = "third_sample_screen_product_price"
    const val PRODUCT_RATING = "third_sample_screen_product_rating"

    // Share button examples
    const val SHARE_BUTTON = "third_sample_screen_share_button"
    const val SHARE_ICON = "third_sample_screen_share_icon"
    const val SHARE_TEXT = "third_sample_screen_share_text"

    // Article examples
    const val ARTICLE_CONTAINER = "third_sample_screen_article_container"
    const val ARTICLE_TITLE = "third_sample_screen_article_title"
    const val ARTICLE_DATE = "third_sample_screen_article_date"
    const val ARTICLE_AUTHOR = "third_sample_screen_article_author"
    const val ARTICLE_READ_TIME = "third_sample_screen_article_read_time"

    // Separate buttons
    const val SEPARATE_BUTTONS_ROW = "third_sample_screen_separate_buttons_row"
    const val SAVE_BUTTON = "third_sample_screen_save_button"
    const val CANCEL_BUTTON = "third_sample_screen_cancel_button"

    const val FOOTER_BUTTON = "third_sample_screen_footer_button"
}
