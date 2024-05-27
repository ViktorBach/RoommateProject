package com.example.roommateproject

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import org.junit.Rule
import org.junit.Test
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.roommateproject.Navigation.Navigation

fun generateRandomString(length: Int): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}
val randomString = generateRandomString(10)

class UITests {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun `Testing user registration`() {
        val drawerState = DrawerState(initialValue = DrawerValue.Closed)

        rule.setContent {
            Navigation(drawerState = drawerState)
        }

        rule.onNodeWithText("Enter email")
            .performTextInput("$randomString@gmail.com")

        rule.onNodeWithText("Enter password")
            .performTextInput(randomString)

        rule.onNodeWithText("Enter username")
            .performTextInput(randomString)

        rule.onNodeWithText("Register")
            .performClick()

        // Wait for navigation to complete
        Thread.sleep(2000)

    }
}
