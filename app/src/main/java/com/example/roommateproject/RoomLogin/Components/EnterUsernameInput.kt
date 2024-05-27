package com.example.roommateproject.RoomLogin.Components

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.RoomLogin.BoxLayout
import com.example.roommateproject.RoomLogin.RoomViewModel

@Composable
fun EnterUserNameInput(navigateFrontPage: () -> Unit) {
    val roomViewModel = viewModel<RoomViewModel>()
    BoxLayout(
        value = roomViewModel.members.joinToString(),
        onValueChange = { newValue ->
            roomViewModel.onMembersChange(newValue.split(",").map { it.trim() })
        },
        labelText = "Enter Members (comma-separated)",
        height = 0.23f
    )
}
