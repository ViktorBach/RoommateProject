package com.example.roommateproject.FrontPage.Components.ListView

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.roommateproject.Services.AccountService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private val accountService: AccountService = AccountService()

    val tasks: SnapshotStateList<ShoppingList> = mutableStateListOf()

    var inputState: MutableState<String?> = mutableStateOf(null)

    init {
        fetchTasks() // Fetch tasks when ViewModel is initialized
    }

    fun createTask(taskTitle: String) {
        tasks.add(0, ShoppingList(taskTitle))
        inputState.value = null
        accountService.addShoppingListItem(taskTitle) // Add to Firestore
    }

    fun fetchTasks() {
        accountService.getShoppingListItems { success, items ->
            if (success) {
                tasks.clear()
                tasks.addAll(items)
            }
        }
    }

    fun toggleTaskCompleted(taskId: String) {
        tasks.firstOrNull { it.id == taskId }?.let { task ->
            val toggledTask = task.toggled()
            tasks[tasks.indexOf(task)] = toggledTask

            CoroutineScope(Dispatchers.IO).launch {
                accountService.updateShoppingListItem(task.title, toggledTask.completed)
            }
        }
    }

    fun onInputChange(input: String?) {
        inputState.value = input
    }

    /*fun removeCompletedItems() {
        tasks.removeAll { it.completed }
    }*/

    fun removeCompletedItems() {
        val completedItems = tasks.filter { it.completed }
        completedItems.forEach { item ->
            CoroutineScope(Dispatchers.IO).launch {
                accountService.removeShoppingListItem(item.title) { success ->
                    if (success) {
                        tasks.remove(item)
                    }
                }
            }
        }
    }
}
