package com.example.roommateproject.FrontPage.Components.ListView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.roommateproject.Services.AccountService
import com.example.roommateproject.ui.theme.*

@Composable
fun AddToListButton(onAddItem: (String) -> Unit) {
    val accountService: AccountService = AccountService();
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var text by remember { mutableStateOf("") }  // Declare text here to use it both in TextField and Button

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = {
                Text(text = "Add New Item")
            },
            text = {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Item Name") }
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (text.isNotBlank()) {
                            onAddItem(text)
                            showDialog = false
                            text = ""  // Reset text after adding
                        }
                    }
                ) {
                    Text("Add")
                }
            },
            dismissButton = {
                Button(onClick = {
                    showDialog = false
                    text = ""  // Reset text when dismissing
                }) {
                    Text("Cancel")
                }
            }
        )
    }

    Box(
        modifier = Modifier
            .background(Color.White)
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        Button(
            onClick = { showDialog = true
                accountService.addEvent(AccountService.EventType.ADD_TO_LIST)
                      },
            colors = ButtonDefaults.buttonColors(
                lightYellow // Set the button background to lightYellow,
            )
        ) {
            Text(
                text = "Add",
                fontFamily = jaldiBoldFontFamily,
                style = Typography.labelSmall,
                color = white
            )
        }
    }
}
