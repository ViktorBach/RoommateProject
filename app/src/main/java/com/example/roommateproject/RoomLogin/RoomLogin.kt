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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.RoomLogin.Components.EnterHouseholdNameTab
import com.example.roommateproject.RoomLogin.Components.EnterPasswordInput
import com.example.roommateproject.RoomLogin.Components.EnterUserNameInput
import com.example.roommateproject.RoomLogin.Components.LoginButton
import com.example.roommateproject.RoomLogin.Components.RegisterButton
import com.example.roommateproject.RoomLogin.Components.SetUpYourHomeTab
import com.example.roommateproject.SharedComponents.Header
import com.example.roommateproject.ui.theme.Typography
import com.example.roommateproject.ui.theme.green
import com.example.roommateproject.ui.theme.jaldiFontFamily
import com.example.roommateproject.ui.theme.karantinaFontFamily
import com.example.roommateproject.ui.theme.katibehFontFamily
import com.example.roommateproject.ui.theme.lightBlue
import com.example.roommateproject.ui.theme.lightYellow
import com.example.roommateproject.ui.theme.orange
import com.example.roommateproject.ui.theme.playFairDisplayFontFamily

@Composable
fun RoomLogin (navigateFrontPage: () -> Unit) {

    val roomViewModel = viewModel<RoomViewModel>()

    Column {
        Spacer(modifier = Modifier.width(16.dp))
        Row {
            Header()
        }
        Row {
            SetUpYourHomeTab()
        }
        Row {
            EnterHouseholdNameTab {}
            }
        Row {
            EnterPasswordInput {}
            }
        Row {
            EnterUserNameInput {}
            }
        Row {
            RegisterButton { roomViewModel.registerNewHouse(navigateFrontPage) }
            LoginButton { roomViewModel.houseLogin(navigateFrontPage) }
        }
    }
}



@Composable
fun RoomPreview () {
    RoomLogin(navigateFrontPage = {})
}