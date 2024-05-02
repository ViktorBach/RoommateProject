package com.example.roommateproject.FrontPage.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.FrontPage.FrontPageViewModel

@Composable
fun LogoutButton(navigateRegisterPage: () -> Unit, function: () -> Unit) {
    val frontPageViewModel = viewModel<FrontPageViewModel>()
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxHeight(0.5f)
            .fillMaxWidth(1f),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { frontPageViewModel.logOut(navigateRegisterPage) }) {
            Text(text = "Logout")
        }
    }
}