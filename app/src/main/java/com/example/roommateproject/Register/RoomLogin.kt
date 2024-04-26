package com.example.roommateproject.Register

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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
                    text = "Set up your home",
                    fontFamily = playFairDisplayFontFamily,
                    style = Typography.titleSmall,
                    color = lightYellow
                )
            }
        }
        Row {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight(0.17f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(
                    value = roomViewModel.houseName,
                    onValueChange = {
                        roomViewModel.onHouseNameChange(it)},
                    label = {
                        Text(
                            "Enter household name",
                            fontFamily = jaldiFontFamily,
                            style = Typography.labelMedium,
                            color = lightBlue
                        )},
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
                    .fillMaxHeight(0.18f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(
                    value = roomViewModel.password,
                    onValueChange = {
                        roomViewModel.onPasswordChange(it)},
                    label = {
                        Text(
                            "Enter password",
                            fontFamily = jaldiFontFamily,
                            style = Typography.labelMedium,
                            color = lightBlue
                        )},
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
                    .fillMaxHeight(0.23f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(
                    value = roomViewModel.members.joinToString(),
                    onValueChange = {
                        roomViewModel.onMembersChange(
                            it.split(",").map { it.trim() })},
                    label = {
                        Text(
                            "Enter members (comma-separated)",
                            fontFamily = jaldiFontFamily,
                            style = Typography.labelMedium,
                            color = lightBlue
                        )},
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
                    onClick = { roomViewModel.registerNewHouse(navigateFrontPage) },
                    colors = ButtonDefaults.buttonColors(
                        lightYellow // Set the text color to lightYellow
                    )
                ) {
                    Text(
                        text = "Register",
                        fontFamily = jaldiFontFamily,
                        style = Typography.labelSmall,
                        color = green
                    )}
                } // Add this closing parenthesis here
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight(0.75f)
                    .fillMaxWidth(1f),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { roomViewModel.houseLogin(navigateFrontPage) },
                    colors = ButtonDefaults.buttonColors(
                        lightYellow // Set the text color to lightYellow
                    )
                ) {
                    Text(
                        text = "Login",
                        fontFamily = jaldiFontFamily,
                        style = Typography.labelSmall,
                        color = green
                    )
                }
            }
        }
    }
}



@Composable
fun RoomPreview () {
    RoomLogin(navigateFrontPage = {})
}