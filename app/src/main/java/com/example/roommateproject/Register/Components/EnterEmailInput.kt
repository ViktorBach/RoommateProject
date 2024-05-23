package com.example.roommateproject.Register.Components

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.Register.BoxLayout
import com.example.roommateproject.Register.RegisterViewModel

@Composable
fun EnterEmailInput (navigateRoomLogin: () -> Unit) {

    val registerViewModel = viewModel<RegisterViewModel>()
    BoxLayout(
        value =  "frank@gmail.com",//registerViewModel.email,
        onValueChange = { newValue -> registerViewModel.onEmailChange(newValue) },
        labelText = "Enter email",
        height = 0.17f
    )
}