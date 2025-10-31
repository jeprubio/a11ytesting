# Fourth Sample Screen - Heading Accessibility Testing

This document explains the FourthSampleScreen implementation and how to test heading accessibility using the updated AccessibilityTestDsl.

## What are Heading Semantics?

Heading semantics in Android accessibility allow screen reader users to navigate through content structure efficiently. Users can:
- Jump between headings using gestures or keyboard shortcuts
- Understand content hierarchy (main sections, subsections, articles)
- Skip to relevant sections quickly
- Get an overview of page structure

## Heading Examples in FourthSampleScreen

### 1. Main Page Heading (Level 1)
```kotlin
Text(
    text = "Accessibility Heading Examples",
    style = MaterialTheme.typography.headlineLarge,
    modifier = Modifier
        .semantics { heading() }
)
```
**Screen Reader**: Announces as "Heading level 1, Accessibility Heading Examples"

### 2. Section Headings (Level 2)
```kotlin
Text(
    text = "Basic Heading Patterns", 
    style = MaterialTheme.typography.headlineMedium,
    modifier = Modifier
        .semantics { heading() }
)
```
**Screen Reader**: Announces as "Heading level 2, Basic Heading Patterns"

### 3. Subsection Headings (Level 3)
```kotlin
Text(
    text = "Simple Text Headings",
    style = MaterialTheme.typography.headlineSmall,
    modifier = Modifier
        .semantics { heading() }
)
```
**Screen Reader**: Announces as "Heading level 3, Simple Text Headings"

### 4. Card Content Headings
```kotlin
Text(
    text = "Product Information",
    style = MaterialTheme.typography.titleLarge,
    modifier = Modifier
        .semantics { heading() }
)
```
Used within cards or sections to provide structure

### 5. Article Headings (Level 4)
```kotlin
Text(
    text = "How to Test Accessibility",
    style = MaterialTheme.typography.titleMedium,
    modifier = Modifier
        .semantics { heading() }
)
```
For individual articles or smaller content sections

### 6. ❌ Bad Example - Missing Heading Semantic
```kotlin
Text(
    text = "Article Title (Missing Heading Semantic)",
    style = MaterialTheme.typography.titleLarge
    // Missing: .semantics { heading() }
)
```
**Problem**: Looks like a heading but screen readers can't navigate to it

## New AccessibilityTestDsl Functions for Testing Headings

### 1. `isHeading()`
Tests if an element is properly marked as a heading:
```kotlin
element(MAIN_HEADING)
    .isHeading()
    .hasText("Accessibility Heading Examples")
```

### 2. `isNotHeading()`
Verifies that an element is NOT marked as a heading:
```kotlin
element(INTRO_TEXT)
    .isNotHeading()
    .hasTextContaining("demonstrates proper heading hierarchy")
```

### 3. `hasText(expectedText: String)`
Tests exact text content:
```kotlin
element(SECTION_HEADING)
    .isHeading()
    .hasText("Basic Heading Patterns")
```

### 4. `hasTextContaining(partialText: String)`
Tests partial text content:
```kotlin
element(ARTICLE_TITLE)
    .isHeading()
    .hasTextContaining("Test Accessibility")
```

## Heading Hierarchy Structure in FourthSampleScreen

```
📋 Accessibility Heading Examples (Level 1)
├── 📄 Basic Heading Patterns (Level 2)
│   ├── 📝 Simple Text Headings (Level 3)
│   └── 📝 Card Headings (Level 3)
│       └── 🏷️ Product Information (Card Heading)
├── 📄 Interactive Headings (Level 2)
│   └── 📝 Clickable Headers (Level 3)
├── 📄 Nested Content Structure (Level 2)
│   └── 📝 Articles (Level 3)
│       ├── 📰 How to Test Accessibility (Level 4)
│       └── 📰 Screen Reader Navigation (Level 4)
└── ❌ Article Title (Missing Heading Semantic) - NOT A HEADING
```

## Testing Strategy

### Test Proper Heading Identification
```kotlin
@Test
fun verifyMainHeadingStructure() {
    composeTestRule.accessibility {
        elementChecks {
            element(MAIN_HEADING)
                .isHeading()
                .hasText("Accessibility Heading Examples")
                
            element(INTRO_TEXT)
                .isNotHeading()
                .hasTextContaining("demonstrates proper heading hierarchy")
        }
    }
}
```

### Test Heading Hierarchy
```kotlin
@Test  
fun verifySectionHeadingHierarchy() {
    composeTestRule.accessibility {
        elementChecks {
            // Level 2 headings
            element(SECTION_HEADING).isHeading()
            element(INTERACTIVE_SECTION_HEADING).isHeading()
            element(NESTED_SECTION_HEADING).isHeading()
            
            // Level 3 headings  
            element(SUBSECTION_HEADING).isHeading()
            element(ARTICLES_HEADING).isHeading()
            
            // Level 4 headings
            element(ARTICLE_TITLE).isHeading()
            element(SECOND_ARTICLE_TITLE).isHeading()
        }
    }
}
```

### Test Non-Heading Content
```kotlin
@Test
fun verifyNonHeadingElementsAreNotHeadings() {
    composeTestRule.accessibility {
        elementChecks {
            // Content should not be headings
            element(INTRO_TEXT).isNotHeading()
            element(BASIC_DESCRIPTION).isNotHeading()
            element(ARTICLE_CONTENT).isNotHeading()
            element(PRODUCT_NAME).isNotHeading()
            element(PRODUCT_PRICE).isNotHeading()
        }
    }
}
```

### Test Bad Examples
```kotlin
@Test
fun verifyBadHeadingExample() {
    composeTestRule.accessibility {
        elementChecks {
            // This looks like a heading but shouldn't be marked as one
            element(MISSING_HEADING_SEMANTIC)
                .isNotHeading()
                .hasText("Article Title (Missing Heading Semantic)")
        }
    }
}
```

### Test Heading Text Content
```kotlin
@Test
fun verifyHeadingTextContent() {
    composeTestRule.accessibility {
        elementChecks {
            element(MAIN_HEADING)
                .hasText("Accessibility Heading Examples")
                
            element(SECTION_HEADING)
                .hasTextContaining("Basic")
                
            element(ARTICLE_TITLE)
                .hasTextContaining("Test Accessibility")
        }
    }
}
```

## Key Benefits for Accessibility

1. **Screen Reader Navigation**: Users can jump between headings efficiently
2. **Content Structure**: Clear hierarchy helps users understand page organization  
3. **Quick Navigation**: Users can skip to relevant sections without reading everything
4. **Overview Generation**: Screen readers can list all headings for content overview
5. **Cognitive Load Reduction**: Well-structured content is easier to understand

## Common Mistakes to Avoid

### ❌ Don't Do This:
```kotlin
// Missing heading semantic
Text(
    text = "Important Section",
    style = MaterialTheme.typography.headlineLarge
    // Missing .semantics { heading() }
)

// Using heading for non-heading content
Text(
    text = "Product price: $99",
    modifier = Modifier.semantics { heading() } // Wrong!
)
```

### ✅ Do This:
```kotlin
// Proper heading
Text(
    text = "Important Section",
    style = MaterialTheme.typography.headlineLarge,
    modifier = Modifier.semantics { heading() }
)

// Regular content (no heading semantic)
Text(text = "Product price: $99")
```

## Running the Tests

To navigate to the Fourth Sample screen and test heading accessibility:

1. Launch the app
2. Tap "Fourth Example" from the home screen
3. The screen demonstrates proper heading hierarchy
4. Run the test suite to verify heading accessibility compliance

This implementation provides comprehensive examples of proper heading structure and robust testing capabilities for heading accessibility in Android Compose applications.
