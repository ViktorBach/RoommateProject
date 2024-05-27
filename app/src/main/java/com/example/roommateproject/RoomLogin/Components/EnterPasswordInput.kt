package com.example.roommateproject.RoomLogin.Components

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.RoomLogin.BoxLayout
import com.example.roommateproject.RoomLogin.RoomViewModel

@Composable
fun EnterPasswordInput(navigateFrontPage: () -> Unit) {
    val roomViewModel = viewModel<RoomViewModel>()
    BoxLayout(
        value = roomViewModel.password,
        onValueChange = { newValue -> roomViewModel.onPasswordChange(newValue) },
        labelText = "Enter Password",
        height = 0.18f
    )
}
