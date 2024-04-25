package com.example.roommateproject.Register

import android.content.res.Resources
import android.graphics.fonts.Font
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.ui.theme.Typography
import com.example.roommateproject.ui.theme.karantinaFontFamily
import com.example.roommateproject.ui.theme.katibehFontFamily
import com.example.roommateproject.ui.theme.lightBlue
import com.example.roommateproject.ui.theme.lightGrey
import com.example.roommateproject.ui.theme.lightYellow
import com.example.roommateproject.ui.theme.orange


@Composable
fun Register (navigateRoomLogin: () -> Unit) {

    val registerViewModel = viewModel<RegisterViewModel>()

    Column {
        Spacer(modifier = Modifier.width(16.dp))
        Row {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight(0.1f),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "H",
                    fontFamily = karantinaFontFamily,
                    style = Typography.titleLarge,
                    color = orange,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight(0.134f),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "omie",
                    fontFamily = katibehFontFamily,
                    style = Typography.titleMedium,
                    color = orange
                )
            }
        }
        Row {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight(0.25f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Welcome Homie!",
                    style = Typography.titleSmall,
                    color = lightYellow
                )
            }
        }
        Row {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight(0.15f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(
                    value = registerViewModel.email,
                    onValueChange = {
                        registerViewModel.onEmailChange(it)
                    },
                    label = {
                        Text(
                            "Enter e-mail",
                            style = Typography.labelMedium,
                            color = lightBlue
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        }
        Row {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight(0.15f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(
                    value = registerViewModel.password,
                    onValueChange = {
                        registerViewModel.onPasswordChange(it)
                    },
                    label = {
                        Text(
                            "Enter password",
                            style = Typography.labelMedium,
                            color = lightBlue,

                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        }
        Row {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight(0.2f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(
                    value = registerViewModel.username,
                    onValueChange = {
                        registerViewModel.onUsernameChange(it)
                    },
                    label = {
                        Text(
                            "Enter username",
                            style = Typography.labelMedium,
                            color = lightBlue
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        }
        Row {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight(0.75f)
                    .fillMaxWidth(0.5f),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { registerViewModel.registerNewUser(navigateRoomLogin) },
                    colors = ButtonDefaults.buttonColors(
                        lightYellow
                    )
                ) {
                    Text(text = "Register",
                        style = Typography.labelMedium,
                        color = lightBlue)
                }
            }
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight(0.75f)
                    .fillMaxWidth(1f),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { registerViewModel.loginWithUser(navigateRoomLogin) },
                    colors = ButtonDefaults.buttonColors(
                        lightYellow // Set the text color to lightYellow
                    )
                ) {
                    Text(text = "Login",
                        style = Typography.labelMedium,
                        color = lightBlue)
                }
            }
        }
    }
}


@Composable
fun RegisterPreview () {
    Register(navigateRoomLogin = {})
}