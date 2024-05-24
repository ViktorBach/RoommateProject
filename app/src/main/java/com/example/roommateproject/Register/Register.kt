package com.example.roommateproject.Register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.Register.Components.EnterEmailInput
import com.example.roommateproject.Register.Components.EnterPasswordInput
import com.example.roommateproject.Register.Components.EnterUsernameInput
import com.example.roommateproject.Register.Components.LoginButton
import com.example.roommateproject.Register.Components.RegisterButton
import com.example.roommateproject.Register.Components.WelcomeHomieTab
import com.example.roommateproject.SharedComponents.CustomLoadingBar
import com.example.roommateproject.SharedComponents.Header
import com.example.roommateproject.ui.theme.Typography
import com.example.roommateproject.ui.theme.jaldiFontFamily
import com.example.roommateproject.ui.theme.white


@Composable
fun Register (navigateRoomLogin: () -> Unit, navigateFrontPage: () -> Unit) {

    val registerViewModel = viewModel<RegisterViewModel>()
    var isLoading by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .background(white)
    ){
        Spacer(modifier = Modifier.width(16.dp))
        Row {
            Header()
        }
        Row {
            WelcomeHomieTab()
        }
        Row {
            EnterEmailInput {}
        }
        Row {
            EnterPasswordInput {}
        }
        Row {
            EnterUsernameInput {}
        }
        Row {
            RegisterButton(
                isLoading = isLoading,
                navigateRoomLogin = navigateRoomLogin,
                onClick = {
                    isLoading = true
                    registerViewModel.registerNewUser {
                        isLoading = false
                        navigateRoomLogin()
                    }
                }
            )
            LoginButton(
                isLoading = isLoading,
                navigateFrontPage = navigateFrontPage,
                onClick = {
                    isLoading = true
                    registerViewModel.loginWithUser {
                        isLoading = false
                        navigateFrontPage()
                    }
                }
            )
        }
    }
    if (isLoading) {
        CustomLoadingBar()
    }
}

@Composable
fun BoxLayout( value: String, onValueChange: (String) -> Unit, labelText: String, height: Float ) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxHeight(height)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(
                    labelText,
                    fontFamily = jaldiFontFamily,
                    style = Typography.labelMedium,
                    color = Color.DarkGray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.LightGray)
        )
    }
}

@Preview
@Composable
fun RegisterPreview () {
    Register(navigateRoomLogin = {}, navigateFrontPage = {})
}