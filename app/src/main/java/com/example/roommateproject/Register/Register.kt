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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.example.roommateproject.Register.Components.EnterEmailInput
import com.example.roommateproject.Register.Components.EnterPasswordInput
import com.example.roommateproject.Register.Components.EnterUsernameInput
import com.example.roommateproject.Register.Components.LoginButton
import com.example.roommateproject.Register.Components.RegisterButton
import com.example.roommateproject.Register.Components.WelcomeHomieTab
import com.example.roommateproject.SharedComponents.Header
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
import com.example.roommateproject.ui.theme.white


@Composable
fun Register (navigateRoomLogin: () -> Unit, navigateFrontPage: () -> Unit) {

    val registerViewModel = viewModel<RegisterViewModel>()

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
            RegisterButton (navigateRoomLogin = navigateRoomLogin)
            LoginButton (navigateFrontPage = navigateFrontPage)
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

@Preview
@Composable
fun RegisterPreview () {
    Register(navigateRoomLogin = {}, navigateFrontPage = {})
}