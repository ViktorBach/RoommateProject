package com.example.roommateproject.FrontPage.Components.ListView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.sp
import com.example.roommateproject.ui.theme.Typography
import com.example.roommateproject.ui.theme.jaldiBoldFontFamily
import com.example.roommateproject.ui.theme.jaldiFontFamily
import com.example.roommateproject.ui.theme.lightYellow
import com.example.roommateproject.ui.theme.orange
import com.example.roommateproject.ui.theme.white

@Composable
fun AddToListButton(onAddItem: (String) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }  // Declare text here to use it both in TextField and Button

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = {
                Text(text = "Add New Item", color = orange, fontFamily = jaldiFontFamily, fontSize = 30.sp)
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
                            text = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        lightYellow
                    )
                ) {
                    Text("Add")
                }
            },
            dismissButton = {
                Button(onClick = {
                        showDialog = false
                     text = ""
                    },
                        colors = ButtonDefaults.buttonColors(
                        lightYellow
                    )
                ) {
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
            onClick = { showDialog = true },
            shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                lightYellow // Set the button background to lightYellow
            ),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(
                start = 15.dp, top = 5.dp, bottom = 5.dp, end = 15.dp), // Set content padding to zero
            modifier = Modifier
        ) {
            Text(
                text = "Add to List",
                fontFamily = jaldiBoldFontFamily,
                style = Typography.labelMedium,
                color = white
            )
        }
    }
}
