package com.telefonica.a11ytesting.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import com.telefonica.a11ytesting.R

@Composable
fun FirstSampleScreen(
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
        title = { Text("First Sample") },
        navigationIcon = {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.testTag(FirstSampleScreenTestTags.BACK_BUTTON),
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back_24dp),
                    contentDescription = stringResource(R.string.back_button_accessibility_description),
                    modifier = Modifier.testTag(FirstSampleScreenTestTags.BACK_BUTTON_IMAGE),
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
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = "This is the first sample item with interesting content",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Content paragraphs
        val paragraphs =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n\nDuis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.".split(
                "\n\n"
            )
        paragraphs.forEach { paragraph ->
            if (paragraph.isNotBlank()) {
                Text(
                    text = paragraph,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
        Button(
            onClick = {
                Toast.makeText(context, "Body Button clicked!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.testTag(FirstSampleScreenTestTags.BODY_BUTTON)
        ) {
            Text(
                text = "Body Button",
            )
        }
    }
}

@Composable
private fun Footer() {
    val context = LocalContext.current
    BottomAppBar {
        Button(
            onClick = {
                Toast.makeText(context, "Bottom Button clicked!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag(FirstSampleScreenTestTags.BOTTOM_BUTTON)
                .padding(horizontal = 16.dp)
        ) {
            Text("Sample Action")
        }
    }
}

object FirstSampleScreenTestTags {
    const val BACK_BUTTON = "first_sample_screen_back_button"
    const val BACK_BUTTON_IMAGE = "first_sample_screen_back_button_image"
    const val BODY_BUTTON = "first_sample_screen_body_button"
    const val BOTTOM_BUTTON = "first_sample_screen_bottom_button"
}
