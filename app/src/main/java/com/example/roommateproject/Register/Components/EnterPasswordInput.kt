package com.example.roommateproject.Register.Components

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.Register.BoxLayout
import com.example.roommateproject.Register.RegisterViewModel

@Composable
fun EnterPasswordInput(navigateRoomLogin: () -> Unit) {
    val registerViewModel = viewModel<RegisterViewModel>()

    BoxLayout(
        value = registerViewModel.password,
        onValueChange = { newValue -> registerViewModel.onPasswordChange(newValue) },
        labelText = "Enter password",
        height = 0.18f,
    )
}
