package com.example.roommateproject.FrontPage

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FrontPage(navigateRegisterPage: () -> Unit, function: () -> Unit) {
    val frontPageViewModel = viewModel<FrontPageViewModel>()

    Button(onClick = { frontPageViewModel.logOut(navigateRegisterPage) }) {
        Text(text = "Logout")
    }
}