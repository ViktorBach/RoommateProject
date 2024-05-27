package com.example.roommateproject.FrontPage.Components.ListView

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.FrontPage.Components.Buttons.ListAddButton
import com.example.roommateproject.ui.theme.Shadow10
import com.example.roommateproject.ui.theme.Typography
import com.example.roommateproject.ui.theme.darkGreen
import com.example.roommateproject.ui.theme.jaldiBoldFontFamily
import com.example.roommateproject.ui.theme.playFairDisplayFontFamily
import com.example.roommateproject.ui.theme.skyBlue
import com.example.roommateproject.ui.theme.white

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
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
        ) {
            Box(
                modifier = Modifier
                    .width(500.dp)
                    .height(100.dp)
                    .background(skyBlue)
                    .padding(bottom = 0.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "WE NEED",
                    fontFamily = playFairDisplayFontFamily,
                    style = Typography.titleSmall,
                    color = darkGreen,
                )
            }
        }
        HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(0.98f)
                    .padding(start = 16.dp, end = 16.dp),
                thickness = 3.dp,
                color = Shadow10
            )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(ScrollState(1)),
        ) {
            Box(
                modifier = Modifier
                    .width(400.dp)
                    .height(5.dp)
                    .background(skyBlue)
                    .padding(16.dp),
            )
            lists.forEach { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onTaskClick(task) }
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = if (task.completed) "✓ ${task.title}" else task.title,
                        modifier = Modifier
                            .clickable { onTaskClick(task) }
                            .padding(vertical = 8.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row {
                ListAddButton(onAddItem = onAddItem) // Add the AddToListButton here

                Spacer(modifier = Modifier.width(16.dp))

                OutlinedButton(
                    modifier = Modifier,
                    border = BorderStroke(2.dp, White),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        Color.Transparent // Set the button background to transparent,
                    ),
                    onClick = onRemoveCompleted
                ) {
                    Text(
                        "Remove Completed",
                        fontFamily = jaldiBoldFontFamily,
                        style = Typography.bodyLarge,
                        color = white
                    )
                }
            }
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun PreviewTasksScreen() {
    ListsScreen(
        lists = ListViewModel.sampleTasks,
        input = null,
        onTaskClick = {},
        onInputChange = {},
        onRemoveCompleted = {},
        onAddItem = {}
    )
}*/
