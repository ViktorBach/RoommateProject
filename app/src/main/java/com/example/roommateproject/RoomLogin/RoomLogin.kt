package com.example.roommateproject.RoomLogin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.RoomLogin.Components.EnterHouseholdNameTab
import com.example.roommateproject.RoomLogin.Components.EnterPasswordInput
import com.example.roommateproject.RoomLogin.Components.EnterUserNameInput
import com.example.roommateproject.RoomLogin.Components.LoginButton
import com.example.roommateproject.RoomLogin.Components.RegisterButton
import com.example.roommateproject.RoomLogin.Components.SetUpYourHomeTab
import com.example.roommateproject.SharedComponents.Header
import com.example.roommateproject.ui.theme.white

@Composable
fun RoomLogin (navigateFrontPage: () -> Unit) {

    val roomViewModel = viewModel<RoomViewModel>()

    Column (
        modifier = Modifier
            .background(white)
    ){
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