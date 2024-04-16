package com.example.roommateproject.Register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RoomLogin (navigateFrontPage: () -> Unit) {

    val roomViewModel = viewModel<RoomViewModel>()

    Box{
        Text(text = "Home")

        Column (
            Modifier.padding(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Set up your home")

            TextField(
                value = roomViewModel.houseName,
                onValueChange = {
                    roomViewModel.onHouseNameChange(it)
                },
                label = { Text("Enter text") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            TextField(
                value = roomViewModel.members,
                onValueChange = {
                    roomViewModel.onMembersChange(it)
                },
                label = { Text("Enter text") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Row {
                Button(onClick = { roomViewModel.registerNewHouse(navigateFrontPage) }) {
                    Text(text = "Continue")
                }
            }
        }
    }
}


@Composable
fun RoomPreview () {
    RoomLogin(navigateFrontPage = {})
}