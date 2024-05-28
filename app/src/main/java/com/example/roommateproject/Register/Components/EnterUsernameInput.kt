package com.example.roommateproject.Register.Components

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.Register.BoxLayout
import com.example.roommateproject.Register.RegisterViewModel

@Composable
fun EnterUsernameInput(navigateRoomLogin: () -> Unit) {
    val registerViewModel = viewModel<RegisterViewModel>()
    BoxLayout(
        value = registerViewModel.username, // registerViewModel.username,
        onValueChange = { newValue -> registerViewModel.onUsernameChange(newValue) },
        labelText = "Enter username",
        height = 0.23f,
    )
}
