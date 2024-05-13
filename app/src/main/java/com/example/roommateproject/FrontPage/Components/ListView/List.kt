package com.example.roommateproject.FrontPage.Components.ListView

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.roommateproject.FrontPage.Components.AddToListButton

@Composable
fun ShoppingListWindow() {
    val shoppingList = remember { mutableStateListOf("Milk", "Eggs", "Bread") }

    Column {
        Text(text = "Shopping List")

        shoppingList.forEach { item ->
            Row {
                Text(text = item)
                IconButton(onClick = {
                    shoppingList.remove(item)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Remove item"
                    )
                }
            }
        }

        AddToListButton(onAddItem = { newItem ->
            if (newItem.isNotBlank()) {
                shoppingList.add(newItem)
            }
        })
    }
}
