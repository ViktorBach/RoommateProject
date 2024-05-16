package com.example.roommateproject.FrontPage.Components.ListView

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roommateproject.FrontPage.Components.AddToListButton
import kotlinx.coroutines.launch
import com.example.roommateproject.R // Adjust this to your package

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val drawerStateLeft = rememberDrawerState(DrawerValue.Closed)
    val drawerStateRight = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val shoppingList = remember { mutableStateListOf("Milk", "Eggs", "Bread") }

    ModalNavigationDrawer(
        drawerState = drawerStateLeft,
        drawerContent = {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Left Navigation Items Here")
                Spacer(modifier = Modifier.height(16.dp))
                AddToListButton { newItem ->
                    if (newItem.isNotBlank()) {
                        shoppingList.add(newItem)
                    }
                }
            }
        },
        content = {
            ModalNavigationDrawer(
                drawerState = drawerStateRight,
                drawerContent = {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Right Navigation Items Here")
                        Spacer(modifier = Modifier.height(16.dp))
                        AddToListButton { newItem ->
                            if (newItem.isNotBlank()) {
                                shoppingList.add(newItem)
                            }
                        }
                    }
                },
                content = {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text("Shopping List App") }
                            )
                        },
                        content = { innerPadding ->
                            Box(modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {
                                        MenuButton(
                                            onClick = { scope.launch { drawerStateLeft.open() } },
                                            painterResource(id = R.drawable.ic_logo) // Replace with your logo
                                        )
                                        MenuButton(
                                            onClick = { /* Your Action Here */ },
                                            painterResource(id = R.drawable.ic_logo) // Replace with your logo
                                        )
                                        MenuButton(
                                            onClick = { /* Your Action Here */ },
                                            painterResource(id = R.drawable.ic_logo) // Replace with your logo
                                        )
                                    }

                                    Column {
                                        MenuButton(
                                            onClick = { scope.launch { drawerStateRight.open() } },
                                            painterResource(id = R.drawable.ic_logo) // Replace with your logo
                                        )
                                        MenuButton(
                                            onClick = { /* Your Action Here */ },
                                            painterResource(id = R.drawable.ic_logo) // Replace with your logo
                                        )
                                        MenuButton(
                                            onClick = { /* Your Action Here */ },
                                            painterResource(id = R.drawable.ic_logo) // Replace with your logo
                                        )
                                    }
                                }

                                Column(
                                    modifier = Modifier.align(Alignment.Center)
                                ) {
                                    ShoppingListWindow(shoppingList)
                                }
                            }
                        }
                    )
                }
            )
        }
    )
}

@Composable
fun MenuButton(onClick: () -> Unit, painter: Painter) {
    IconButton(onClick = onClick) {
        Image(
            painter = painter,
            contentDescription = "Menu",
            modifier = Modifier.size(24.dp) // Adjust size as needed
        )
    }
}

@Composable
fun ShoppingListWindow(shoppingList: MutableList<String>) {
    Column {
        shoppingList.forEach { item ->
            Row {
                Text(item)
                // Optionally add button to manage items
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}
