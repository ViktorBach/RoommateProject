package com.example.roommateproject.RoomLogin

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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.RoomLogin.Components.EnterHouseholdNameTab
import com.example.roommateproject.RoomLogin.Components.EnterPasswordInput
import com.example.roommateproject.RoomLogin.Components.EnterUserNameInput
import com.example.roommateproject.RoomLogin.Components.RoomLoginButton
import com.example.roommateproject.RoomLogin.Components.RoomRegisterButton
import com.example.roommateproject.RoomLogin.Components.SetUpYourHomeTab
import com.example.roommateproject.SharedComponents.CustomLoadingBar
import com.example.roommateproject.SharedComponents.Header
import com.example.roommateproject.ui.theme.Typography
import com.example.roommateproject.ui.theme.jaldiFontFamily
import com.example.roommateproject.ui.theme.white

/*****************************************************************************/
                // Room Login Screen Composable Functions //
/*****************************************************************************/
@Composable
fun RoomLogin (navigateFrontPage: () -> Unit) {

    val roomViewModel = viewModel<RoomViewModel>()
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(white)
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Row {
            Header() // Calls the Header Composable Function
        }
        Row {
            SetUpYourHomeTab() // Calls the SetUpYourHomeTab Composable Function
        }
        Row {
            EnterHouseholdNameTab {} // Calls the EnterHouseholdNameTab Composable Function
        }
        Row {
            EnterPasswordInput {} // Calls the EnterPasswordInput Composable Function
        }
        Row {
            EnterUserNameInput {} // Calls the EnterUserNameInput Composable Function
        }
        // Buttons Row
        Row {
            RoomRegisterButton(
                isLoading = isLoading,
                navigateFrontPage = navigateFrontPage,
                onClick = {
                    isLoading = true
                    roomViewModel.registerNewHouse {
                        isLoading = false
                        navigateFrontPage()
                    }
                }
            )
            RoomLoginButton(
                isLoading = isLoading,
                navigateFrontPage = navigateFrontPage,
                onClick = {
                    isLoading = true
                    roomViewModel.houseLogin {
                        isLoading = false
                        navigateFrontPage()
                    }
                }
            )
        }
        // Loading Bar
        if (isLoading) {
            CustomLoadingBar()
        }
    }
}

// Box Layout for Input Fields
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
fun RoomPreview () {
    RoomLogin(navigateFrontPage = {})
}