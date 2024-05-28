package com.example.roommateproject.FrontPage.Components.Buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.roommateproject.ui.theme.Typography
import com.example.roommateproject.ui.theme.jaldiBoldFontFamily
import com.example.roommateproject.ui.theme.white

/*****************************************************************************/
                            // List Add Button //
/*****************************************************************************/
@Composable
fun ListAddButton(onAddItem: (String) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
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
            .background(Color.Transparent)
    ) {
        OutlinedButton(modifier = Modifier,
            onClick = { showDialog = true },
            border = BorderStroke(2.dp, Color.White),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                Color.Transparent // Set the button background to lightYellow,
            )
        ) {
            Text(
                text = "Add",
                fontFamily = jaldiBoldFontFamily,
                style = Typography.bodyLarge,
                color = white
            )
        }
    }
}
