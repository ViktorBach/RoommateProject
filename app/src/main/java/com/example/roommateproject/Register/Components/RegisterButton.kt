package com.example.roommateproject.Register.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.Register.RegisterViewModel
import com.example.roommateproject.ui.theme.Typography
import com.example.roommateproject.ui.theme.darkGreen
import com.example.roommateproject.ui.theme.jaldiFontFamily
import com.example.roommateproject.ui.theme.lightYellow

@Composable
fun RegisterButton(
    isLoading: Boolean,
    navigateRoomLogin: () -> Unit,
    onClick: () -> Unit
) {

    val registerViewModel = viewModel<RegisterViewModel>()

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxHeight(0.75f)
            .fillMaxWidth(0.5f),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                if (!isLoading) {
                    if (registerViewModel.email.isNotEmpty() &&
                        registerViewModel.password.isNotEmpty() &&
                        registerViewModel.username.isNotEmpty()
                    ) {
                        onClick()
                    } else {
                        println("Error: All fields must be filled")
                        // Show an appropriate error message to the user (e.g., Toast)
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                lightYellow
            )
        ) {
            Text(
                text = "Register",
                fontFamily = jaldiFontFamily,
                style = Typography.labelSmall,
                color = darkGreen
            )
        }
    }
}
