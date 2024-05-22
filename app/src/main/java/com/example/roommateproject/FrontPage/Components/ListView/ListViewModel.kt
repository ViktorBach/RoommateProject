package com.example.roommateproject.FrontPage.Components.ListView

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.roommateproject.Services.AccountService

class ListViewModel : ViewModel() {

    private val accountService: AccountService = AccountService()

    val tasks: SnapshotStateList<ShoppingList> = mutableStateListOf(*sampleTasks.toTypedArray())

    var inputState: MutableState<String?> = mutableStateOf(null)

    fun createTask(taskTitle: String) {
        tasks.add(0, ShoppingList(taskTitle))
        inputState.value = null
        accountService.addShoppingListItem(taskTitle) // Add to Firestore
    }

    fun toggleTaskCompleted(taskId: String) {
        tasks.firstOrNull { it.id == taskId }?.let {
            tasks[tasks.indexOf(it)] = it.toggled()
        }
    }

    fun onInputChange(input: String?) {
        inputState.value = input
    }

    fun removeCompletedItems() {
        tasks.removeAll { it.completed }
    }

    companion object {
        val sampleTasks = listOf(
            ShoppingList("Sell my iPhone", completed = false),
            ShoppingList("Get hired at Shape", completed = false),
            ShoppingList("Return library books", completed = false),
            ShoppingList("Read “Best Interface is No Interface by Golden Krishna”", completed = false),
            ShoppingList("Go to Sydhavnstippen", completed = true),
            ShoppingList("Visit parents", completed = true),
            ShoppingList("Attend Shape Guest Lecture", completed = true)
        )
    }
}
