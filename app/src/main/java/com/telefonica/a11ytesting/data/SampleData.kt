package com.telefonica.a11ytesting.data

data class SampleItem(
    val id: Int,
    val title: String,
    val description: String,
    val content: String
)

object SampleData {
    val items = listOf(
        SampleItem(
            id = 1,
            title = "First Sample",
            description = "This is the first sample item with interesting content",
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n\nDuis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        ),
    )
}
