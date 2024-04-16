package com.example.roommateproject.Register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.example.roommateproject.Services.AccountService

class RoomViewModel: ViewModel() {
    private val accountService: AccountService = AccountService();

    var houseName by mutableStateOf("")
        private set;

    var members by mutableStateOf("")
        private set;

    fun onHouseNameChange(email: String) {
        this.houseName = email;
    }

    fun onMembersChange(password: String) {
        this.members = password;
    }

    fun registerNewHouse(navigateFrontPage: () -> Unit) {
        accountService.authenticate(houseName, members) { success, errorMessage ->
            if (success as Boolean) {
                println("House registered successfully!")
                navigateFrontPage() // Navigates user to the RoomLogin screen
            } else {
                println("House registration failed: $errorMessage") // Registration failed
            }
        }
    }
}

@Preview
@Composable
fun HousePreview () {
    RoomLogin(navigateFrontPage = {})
}