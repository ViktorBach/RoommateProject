package com.example.roommateproject


import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.roommateproject.Navigation.Navigation
import com.example.roommateproject.Register.RegisterViewModel
import com.example.roommateproject.Services.AccountService

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
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
        rule.setContent { Navigation() }

            rule.onNodeWithText("Enter email")
                .performTextInput("$randomString@gmail.com")

            rule.onNodeWithText("Enter password")
                .performTextInput(randomString)

            rule.onNodeWithText("Enter username")
                .performTextInput(randomString)

            rule.onNodeWithText("Register")
                .performClick()

            Thread.sleep(2000)

            rule.onNodeWithText("Enter household name")
                .assertExists()

            rule.onNodeWithText("Enter household name")
                .performTextInput(randomString)

            rule.onNodeWithText("Enter password")
                .performTextInput(randomString)

            rule.onNodeWithText("Enter members (comma-separated)")
                .performTextInput(randomString)

            rule.onNodeWithText("Register")
                .performClick()

            Thread.sleep(2000)

            rule.onNodeWithText("Logout")
                .assertExists()

            rule.onNodeWithText("Logout")
                .performClick()

            Thread.sleep(2000)



    }
}
    /*

@get:Rule
val rule = createComposeRule()


@Test
fun registerNewHome() {
rule.setContent { Navigation() }

rule.onNodeWithText("Enter household name")
    .performClick()
    .performTextInput(randomString)

rule.onNodeWithText("Enter password")
    .performClick()
    .performTextInput(randomString)

rule.onNodeWithText("Enter members (comma-separated)")
    .performClick()
    .performTextInput(randomString)

rule.onNodeWithText("Register")
    .performClick()

rule.waitForIdle()

/*rule.onNodeWithText("Logout")
    .assertExists()

 */
} */

/*
class RegisterViewModelTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: RegisterViewModel
    private val accountService = mock(AccountService::class.java)

    @Before
    fun setup() {
        viewModel = RegisterViewModel(accountService)
        composeTestRule.setContent {
            YourComposeScreen(viewModel) // This should be the screen that uses the ViewModel
        }
    }

    @Test
    fun `test successful registration`() {
        whenever(accountService.authenticate(any(), any(), any(), any())).thenAnswer {
            (it.arguments[3] as (Boolean, String) -> Unit)(true, "")
        }

        composeTestRule.onNodeWithText("Enter email").performTextInput("test@example.com")
        composeTestRule.onNodeWithText("Enter password").performTextInput("password")
        composeTestRule.onNodeWithText("Enter username").performTextInput("username")
        composeTestRule.onNodeWithText("Register").performClick()

        composeTestRule.waitForIdle()
        // Additional assertions or checks can be made here
    }
}

 */
