package com.example.roommateproject.Register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.roommateproject.Services.AccountService

class RegisterViewModel: ViewModel() {
    private val accountService: AccountService = AccountService();

    var email by mutableStateOf("")
        private set;

    var password by mutableStateOf("")
        private set;

    var username by mutableStateOf("")
        private set

    fun onEmailChange(email: String) {
        this.email = email;
    }

    fun onPasswordChange(password: String) {
        this.password = password;
    }

    fun onUsernameChange(username: String) {
        this.username = username
    }

    fun registerNewUser(navigateRoomLogin: () -> Unit) {
        accountService.authenticate(email, password, username) { success, errorMessage ->
            if (success as Boolean) {
                println("User registered successfully!")
                navigateRoomLogin() // Navigates user to the RoomLogin screen
            } else {
                println("Registration failed: $errorMessage") // Registration failed
            }
        }
    }

    fun loginWithUser(navigateFrontPage: () -> Unit) {
        accountService.login(email, password) { success, errorMessage ->
            if (success as Boolean) {
                println("Login successful!!!!")
                navigateFrontPage() // Navigates user to the frontpage screen
            } else {
                println("Login failed: $errorMessage") // Login failed
            }
        }
    }
}