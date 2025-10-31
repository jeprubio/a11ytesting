package com.telefonica.a11ytesting.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onFirstItemClicked: () -> Unit,
    onSecondItemClicked: () -> Unit,
    onThirdItemClicked: () -> Unit,
    onFourthItemClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Card(
                    onClick = onFirstItemClicked,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = "First Example",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier
                            .padding(vertical = 24.dp, horizontal = 16.dp)
                    )
                }
            }
            item {
                Card(
                    onClick = onSecondItemClicked,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = "Second Example",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier
                            .padding(vertical = 24.dp, horizontal = 16.dp)
                    )
                }
            }
            item {
                Card(
                    onClick = onThirdItemClicked,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = "Third Example",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier
                            .padding(vertical = 24.dp, horizontal = 16.dp)
                    )
                }
            }
            item {
                Card(
                    onClick = onFourthItemClicked,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = "Fourth Example",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier
                            .padding(vertical = 24.dp, horizontal = 16.dp)
                    )
                }
            }
        }
    }
}
