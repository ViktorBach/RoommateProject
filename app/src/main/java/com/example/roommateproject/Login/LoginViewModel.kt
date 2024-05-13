package com.example.roommateproject.Login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roommateproject.Login.Login
import com.example.roommateproject.Services.AccountService
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
        private val accountService: AccountService = AccountService();

    private var email by mutableStateOf("")

    private var password by mutableStateOf("")

    private var username by mutableStateOf("")

        fun onEmailChange(email: String) {
            this.email = email;
        }

        fun onPasswordChange(password: String) {
            this.password = password;
        }

        fun onUsernameChange(username: String) {
            this.username = username
        }

    fun loginWithUser(navigateFrontPage: () -> Unit) {
        viewModelScope.launch {
            accountService.login(email, password) { success, errorMessage ->
                if (success as Boolean) {
                    println("Login successful!!!!")
                    viewModelScope.launch {
                        accountService.getEvents()
                    }
                    navigateFrontPage() // Navigates user to the frontpage screen
                } else {
                    println("Login failed: $errorMessage") // Login failed
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginPreview () {
    Login(navigateFrontPage = {})
}