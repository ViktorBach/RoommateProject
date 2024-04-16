package com.example.roommateproject.Register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Register (navigateRoomLogin: () -> Unit) {

    val registerViewModel = viewModel<RegisterViewModel>()

    Box{
        Text(text = "Home")

        Column (
            Modifier.padding(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome home")

            TextField(
                value = registerViewModel.email,
                onValueChange = {
                    registerViewModel.onEmailChange(it)
                },
                label = { Text("Enter text") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            TextField(
                value = registerViewModel.password,
                onValueChange = {
                    registerViewModel.onPasswordChange(it)
                },
                label = { Text("Enter text") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Row {
                Button(onClick = { registerViewModel.registerNewUser(navigateRoomLogin) }
            ) {
                    Text(text = "Register")
                }
                Button(onClick = { registerViewModel.loginWithUser(navigateRoomLogin) }) {
                    Text(text = "Login")
                }
            }
        }
    }
}

@Preview
@Composable
fun RegisterPreview () {
    Register(navigateRoomLogin = {})
}