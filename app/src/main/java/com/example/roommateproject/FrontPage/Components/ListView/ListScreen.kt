package com.example.roommateproject.FrontPage.Components.ListView

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.clickable
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ListScreenStateful(listViewModel: ListViewModel = viewModel()) {
    val lists: List<ShoppingList> = listViewModel.tasks
    val input: State<String?> = listViewModel.inputState

    ListsScreen(
        lists = lists,
        input = input.value,
        onTaskClick = { listViewModel.toggleTaskCompleted(it.id) },
        onInputChange = { listViewModel.onInputChange(it) },
        onSubmitInput = { listViewModel.createTask(it) },
        onRemoveCompleted = { listViewModel.removeCompletedItems() }
    )
}

@Composable
fun ListsScreen(
    lists: List<ShoppingList>,
    input: String?,
    onTaskClick: (ShoppingList) -> Unit,
    onInputChange: (String) -> Unit,
    onSubmitInput: (String) -> Unit,
    onRemoveCompleted: () -> Unit
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
        Text(
            "Add Item",
            modifier = Modifier
                .clickable {
                    if (!input.isNullOrBlank()) {
                        onSubmitInput(input)
                    }
                }
                .padding(bottom = 16.dp)
        )
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
        onSubmitInput = {},
        onRemoveCompleted = {}
    )
}