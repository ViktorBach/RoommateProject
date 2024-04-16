package com.example.roommateproject.Register

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.roommateproject.Services.AccountService

class RegisterViewModel: ViewModel() {
    private val accountService: AccountService = AccountService();

    var email by mutableStateOf("")
        private set;

    var password by mutableStateOf("")
        private set;

    fun onEmailChange(email: String) {
        this.email = email;
    }

    fun onPasswordChange(password: String) {
        this.password = password;
    }

    fun registerNewUser(navigateRoomLogin: () -> Unit) {
        accountService.authenticate(email, password) { success, errorMessage ->
            if (success as Boolean) {
                println("User registered successfully!")
                navigateRoomLogin() // Navigates user to the RoomLogin screen
            } else {
                println("Registration failed: $errorMessage") // Registration failed
            }
        }
    }

    fun loginWithUser(navigateRoomLogin: () -> Unit) {
        accountService.login(email, password) { success, errorMessage ->
            if (success as Boolean) {
                println("Login successful!!!!")
                navigateRoomLogin() // Navigates user to the RoomLogin screen
            } else {
                println("Login failed: $errorMessage") // Login failed
            }
        }
    }
}