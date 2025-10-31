# Third Sample Screen - Merged Content Descriptions Testing

This document explains the ThirdSampleScreen implementation and how to test merged content descriptions using the updated AccessibilityTestDsl.

## What are Merged Content Descriptions?

In Android accessibility, when multiple UI elements are semantically grouped together (like when they're inside a clickable container), their individual text content gets merged into a single content description for screen readers. This is essential for providing a good accessibility experience.

## Examples in ThirdSampleScreen

### 1. Product Card with Merged Content
```kotlin
Card(
    modifier = Modifier
        .clickable { /* action */ }
        .semantics {
            contentDescription = "Product: Premium Headphones, Price: $199, 4.5 stars rating, Add to cart"
        }
) {
    // Individual elements: Image, Title, Price, Rating
    // These get merged into the single content description above
}
```

### 2. Button with Icon and Text
```kotlin
Button(onClick = { /* action */ }) {
    Icon(contentDescription = null) // Will merge with button text
    Text("Share this product")
}
```
The screen reader announces: "Share this product, Button"

### 3. Complex Merged Content with clearAndSetSemantics
```kotlin
Box(
    modifier = Modifier
        .clickable { /* action */ }
        .clearAndSetSemantics {
            contentDescription = "Article: How to test accessibility, Published 2 hours ago by John Doe, 5 minute read, Tap to open"
        }
) {
    // Multiple text elements that are completely replaced by the custom content description
}
```

## New AccessibilityTestDsl Functions for Testing Merged Content

### 1. `hasContentDescriptionContaining(partialText: String)`
Tests if the content description contains a specific substring:
```kotlin
element(PRODUCT_CARD)
    .hasContentDescriptionContaining("Premium Headphones")
    .hasContentDescriptionContaining("$199")
```

### 2. `hasContentDescriptionMatching(regex: Regex)`
Tests if the content description matches a regex pattern:
```kotlin
element(PRODUCT_CARD)
    .hasContentDescriptionMatching(Regex(".*\\$\\d+.*")) // Price pattern
    .hasContentDescriptionMatching(Regex(".*\\d+\\.\\d+ stars.*")) // Rating pattern
```

### 3. `hasMergedContentDescription()`
Verifies that an element has a merged content description (has both content description and clickable actions):
```kotlin
element(PRODUCT_CARD)
    .hasMergedContentDescription()
```

### 4. `hasNoContentDescription()`
Verifies that individual child elements don't have their own content descriptions:
```kotlin
element(PRODUCT_TITLE, unmerged = true)
    .hasNoContentDescription()
```


## Testing Strategy

### Test Merged Elements Have Proper Descriptions
```kotlin
@Test
fun verifyMergedContentDescriptions() {
    composeTestRule.accessibility {
        elementChecks {
            element(PRODUCT_CARD)
                .hasContentDescription("Product: Premium Headphones, Price: $199, 4.5 stars rating, Add to cart")
                .hasMergedContentDescription()
        }
    }
}
```

### Test Individual Elements Don't Have Descriptions
```kotlin
@Test  
fun verifyIndividualElementsHaveNoDescriptions() {
    composeTestRule.accessibility {
        elementChecks {
            element(PRODUCT_IMAGE, unmerged = true).hasNoContentDescription()
            element(PRODUCT_TITLE, unmerged = true).hasNoContentDescription()
            element(PRODUCT_PRICE, unmerged = true).hasNoContentDescription()
        }
    }
}
```

### Test Partial Content Matching
```kotlin
@Test
fun verifyPartialContentDescriptionMatching() {
    composeTestRule.accessibility {
        elementChecks {
            element(PRODUCT_CARD)
                .hasContentDescriptionContaining("Premium Headphones")
                .hasContentDescriptionContaining("$199")
                .hasContentDescriptionContaining("Add to cart")
        }
    }
}
```

### Test Regex Pattern Matching
```kotlin
@Test
fun verifyRegexPatternMatching() {
    composeTestRule.accessibility {
        elementChecks {
            element(PRODUCT_CARD)
                .hasContentDescriptionMatching(Regex(".*\\$\\d+.*")) // Price format
                .hasContentDescriptionMatching(Regex(".*\\d+\\.\\d+ stars.*")) // Rating format
        }
    }
}
```

## Key Benefits

1. **Comprehensive Testing**: Can verify both that merged descriptions exist and are correct
2. **Flexible Matching**: Support for exact matches, partial matches, and regex patterns  
3. **Child Element Verification**: Ensures individual elements don't interfere with merged descriptions
4. **Real-world Examples**: Tests common UI patterns like product cards, buttons with icons, and articles

## Running the Tests

To navigate to the Third Sample screen and test merged content descriptions:

1. Launch the app
2. Tap "Third Example" from the home screen
3. The screen demonstrates various merged content description patterns
4. Run the test suite to verify accessibility compliance

This implementation provides a robust framework for testing one of the most important aspects of mobile accessibility - ensuring that grouped content is properly announced as a cohesive unit by screen readers.
