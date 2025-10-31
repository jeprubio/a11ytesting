package com.telefonica.a11ytesting.library

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.isFocused
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.pressKey
import androidx.compose.ui.test.printToLog

class AccessibilityTestDsl(private val composeTestRule: ComposeTestRule) {
    fun elementChecks(block: AccessibilityAssertions.() -> Unit) {
        val assertions = AccessibilityAssertions(composeTestRule)
        assertions.block()
    }
    fun keyboardFocus(block: KeyboardA11y.() -> Unit) {
        KeyboardA11y(composeTestRule).block()
    }
}

class AccessibilityAssertions(private val composeTestRule: ComposeTestRule) {
    fun element(identifier: String, unmerged: Boolean = false): ElementAccessibility {
        return ElementAccessibility(composeTestRule, identifier, ElementAccessibility.MatchType.TAG, unmerged)
    }
    fun elementWithText(text: String, unmerged: Boolean = false): ElementAccessibility {
        return ElementAccessibility(composeTestRule, text, ElementAccessibility.MatchType.TEXT, unmerged)
    }
}

class ElementAccessibility(
    private val composeTestRule: ComposeTestRule,
    private val identifier: String,
    private val matchType: MatchType = MatchType.TAG,
    private val useUnmergedTree: Boolean = false,
) {
    enum class MatchType {
        TAG, TEXT
    }
    private val node: SemanticsNodeInteraction
        get() = when (matchType) {
            MatchType.TEXT -> composeTestRule.onNodeWithText(identifier, useUnmergedTree = useUnmergedTree)
            MatchType.TAG -> composeTestRule.onNodeWithTag(identifier, useUnmergedTree = useUnmergedTree)
        }
    infix fun hasContentDescription(expected: String): ElementAccessibility =
        assertWithTreeDump("expected contentDescription='$expected'") {
            assert(androidx.compose.ui.test.hasContentDescription(expected))
        }

    fun hasContentDescriptionContaining(partialText: String): ElementAccessibility =
        assertWithTreeDump("expected contentDescription containing '$partialText'") {
            assert(
                SemanticsMatcher("content description contains '$partialText'") { semanticsNode ->
                    val list = semanticsNode.config.getOrNull(SemanticsProperties.ContentDescription)
                    list?.any { it.contains(partialText, ignoreCase = true) } == true
                }
            )
        }

    fun hasContentDescriptionMatching(regex: Regex): ElementAccessibility =
        assertWithTreeDump("expected contentDescription matching pattern '${regex.pattern}'") {
            assert(
                SemanticsMatcher("content description matches pattern '${regex.pattern}'") { semanticsNode ->
                    val list = semanticsNode.config.getOrNull(SemanticsProperties.ContentDescription)
                    list?.any { regex.matches(it) } == true
                }
            )
        }

    fun hasMergedContentDescription(): ElementAccessibility =
        assertWithTreeDump("expected merged content description (multiple elements combined)") {
            assert(
                SemanticsMatcher("has merged content description") { semanticsNode ->
                    val contentDescList = semanticsNode.config.getOrNull(SemanticsProperties.ContentDescription)
                    val hasContentDesc = contentDescList?.isNotEmpty() == true

                    val textList = semanticsNode.config.getOrNull(SemanticsProperties.Text)
                    val hasText = textList?.isNotEmpty() == true

                    // Check if this is a merged node by looking for clickable/selectable actions
                    // and either content description or text (for buttons)
                    val hasClickableAction = semanticsNode.config.contains(SemanticsActions.OnClick) ||
                                           semanticsNode.config.contains(SemanticsProperties.Selected) ||
                                           semanticsNode.config.contains(SemanticsProperties.Role)

                    // Element has merged semantics if it has clickable actions and either content description or text
                    (hasContentDesc || hasText) && hasClickableAction
                }
            )
        }

    fun hasAnyContentDescriptionDefined(): ElementAccessibility {
        node.assert(SemanticsMatcher.keyIsDefined(SemanticsProperties.ContentDescription))
        return this
    }

    fun hasNonBlankContentDescription(): ElementAccessibility =
        assertWithTreeDump("expected non-blank contentDescription") {
            assert(
                SemanticsMatcher("has non-blank content description") { semanticsNode ->
                    val list = semanticsNode.config.getOrNull(SemanticsProperties.ContentDescription)
                    list != null && list.any { it.isNotBlank() }
                }
            )
        }

    fun hasNoContentDescription(): ElementAccessibility =
        assertWithTreeDump("expected no contentDescription") {
            assert(
                SemanticsMatcher("has no content description") { semanticsNode ->
                    val list = semanticsNode.config.getOrNull(SemanticsProperties.ContentDescription)
                    list.isNullOrEmpty() || list.all { it.isBlank() }
                }
            )
        }


    private inline fun assertWithTreeDump(
        description: String,
        crossinline block: SemanticsNodeInteraction.() -> Unit
    ): ElementAccessibility {
        try {
            block(node)
        } catch (e: AssertionError) {
            composeTestRule.onRoot().printToLog("A11Y")
            throw AssertionError("A11y assertion failed: $description\n${e.message}", e)
        }
        return this
    }
}

class KeyboardA11y(private val rule: ComposeTestRule) {
    fun assertNoFocus(): KeyboardA11y {
        rule.onAllNodes(isFocused()).assertCountEquals(0)
        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun next(): FocusStep {
        rule.onRoot().performKeyInput { pressKey(Key.Tab) }
        rule.waitForIdle()
        rule.onRoot(useUnmergedTree = true).printToLog(A11Y_TAG)
        return FocusStep(rule, this)
    }

    @OptIn(ExperimentalTestApi::class)
    fun previous(): FocusStep {
        rule.onRoot().performKeyInput {
            keyDown(Key.ShiftLeft)
            pressKey(Key.Tab)
            keyUp(Key.ShiftLeft)
        }
        rule.waitForIdle()
        return FocusStep(rule, this)
    }
    fun assertFocusOn(tag: String, unmerged: Boolean = false): KeyboardA11y {
        rule.onNodeWithTag(tag, useUnmergedTree = unmerged).assertIsFocused()
        return this
    }

    class FocusStep(
        private val rule: ComposeTestRule,
        private val parent: KeyboardA11y
    ) {

        infix fun shouldFocus(tag: String): KeyboardA11y = shouldFocus(tag, unmerged = true)
        fun shouldFocus(tag: String, unmerged: Boolean): KeyboardA11y {
            parent.assertFocusOn(tag, unmerged)
            return parent
        }

        fun focusedIs(tag: String, unmerged: Boolean = false): KeyboardA11y {
            parent.assertFocusOn(tag, unmerged)
            return parent
        }
        fun focusedMatches(matcher: SemanticsMatcher): KeyboardA11y {
            rule.onAllNodes(isFocused()).assertCountEquals(1)
            rule.onAllNodes(isFocused())[0].assert(matcher)
            return parent
        }
    }

    companion object {
        private const val A11Y_TAG = "A11Y"
    }
}

fun ComposeTestRule.accessibility(block: AccessibilityTestDsl.() -> Unit) {
    val dsl = AccessibilityTestDsl(this)
    dsl.block()
}
