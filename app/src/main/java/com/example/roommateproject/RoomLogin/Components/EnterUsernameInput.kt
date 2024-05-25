package com.example.roommateproject.RoomLogin.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.RoomLogin.BoxLayout
import com.example.roommateproject.RoomLogin.RoomViewModel
import com.example.roommateproject.ui.theme.Typography
import com.example.roommateproject.ui.theme.jaldiFontFamily
import com.example.roommateproject.ui.theme.lightBlue

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