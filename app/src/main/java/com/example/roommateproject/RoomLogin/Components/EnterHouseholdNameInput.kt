package com.example.roommateproject.RoomLogin.Components

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.RoomLogin.BoxLayout
import com.example.roommateproject.RoomLogin.RoomViewModel

// Sofie
@Composable
fun EnterHouseholdNameTab(navigateFrontPage: () -> Unit) {
    val roomViewModel = viewModel<RoomViewModel>()
    BoxLayout(
        value = roomViewModel.houseName,
        onValueChange = { newValue -> roomViewModel.onHouseNameChange(newValue) },
        labelText = "Enter Household Name",
        height = 0.17f,
    )
}
