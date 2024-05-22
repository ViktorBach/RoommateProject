package com.example.roommateproject.FrontPage.Components.ListView

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.Services.AccountService

@Composable
fun ListScreenStateful(listViewModel: ListViewModel = viewModel()) {
    val lists: List<ShoppingList> = listViewModel.tasks
    val input: State<String?> = listViewModel.inputState

    ListsScreen(
        lists = lists,
        input = input.value,
        onTaskClick = { listViewModel.toggleTaskCompleted(it.id) },
        onInputChange = { listViewModel.onInputChange(it) },
        onRemoveCompleted = { listViewModel.removeCompletedItems() },
        onAddItem = { listViewModel.createTask(it) } // Pass the add item function
    )
}

@Composable
fun ListsScreen(
    lists: List<ShoppingList>,
    input: String?,
    onTaskClick: (ShoppingList) -> Unit,
    onInputChange: (String) -> Unit,
    onRemoveCompleted: () -> Unit,
    onAddItem: (String) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        BasicTextField(
            value = input ?: "",
            onValueChange = {
                onInputChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        AddToListButton(onAddItem = onAddItem) // Add the AddToListButton here
        Spacer(modifier = Modifier.height(16.dp))
        lists.forEach { task ->
            Text(
                text = if (task.completed) "✓ ${task.title}" else task.title,
                modifier = Modifier
                    .clickable { onTaskClick(task) }
                    .padding(vertical = 8.dp)
            )
        }
        Button(onClick = onRemoveCompleted) {
            Text("Remove Completed")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTasksScreen() {
    ListsScreen(
        lists = ListViewModel.sampleTasks,
        input = null,
        onTaskClick = {},
        onInputChange = {},
        onRemoveCompleted = {},
        onAddItem = {} // Add this parameter
    )
}