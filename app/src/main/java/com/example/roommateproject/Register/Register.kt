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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import com.example.roommateproject.ui.theme.green
import com.example.roommateproject.ui.theme.jaldiFontFamily
import com.example.roommateproject.ui.theme.karantinaFontFamily
import com.example.roommateproject.ui.theme.katibehFontFamily
import com.example.roommateproject.ui.theme.lightBlue
import com.example.roommateproject.ui.theme.lightGrey
import com.example.roommateproject.ui.theme.lightYellow
import com.example.roommateproject.ui.theme.orange
import com.example.roommateproject.ui.theme.playFairDisplayFontFamily
import com.google.type.Fraction


@OptIn(ExperimentalMaterial3Api::class)
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
                    .fillMaxHeight(0.13f),
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
                    .fillMaxHeight(0.2f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Welcome Homie!",
                    fontFamily = playFairDisplayFontFamily,
                    style = Typography.titleSmall,
                    color = lightYellow,
                )
            }
        }
        Row {
            BoxLayout(
                value = registerViewModel.email,
                onValueChange = { newValue -> registerViewModel.onEmailChange(newValue) },
                labelText = "Enter email",
                height = 0.17f
            )

        }
        Row {
            BoxLayout(
                value = registerViewModel.password,
                onValueChange = { newValue -> registerViewModel.onPasswordChange(newValue) },
                labelText = "Enter password",
                height = 0.18f
            )

        }
        Row {
            BoxLayout(
                value = registerViewModel.username,
                onValueChange = { newValue -> registerViewModel.onUsernameChange(newValue) },
                labelText = "Enter username",
                height = 0.23f
            )
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
                        fontFamily = jaldiFontFamily,
                        style = Typography.labelSmall,
                        color = green)
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
                        fontFamily = jaldiFontFamily,
                        style = Typography.labelSmall,
                        color = green)
                }
            }
        }
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

@Composable
fun RegisterPreview () {
    Register(navigateRoomLogin = {})
}