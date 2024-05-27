package com.example.roommateproject.FrontPage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.roommateproject.FrontPage.Components.ListView.ShoppingList
import com.example.roommateproject.Services.AccountService
import com.google.firebase.auth.FirebaseAuth

class FrontPageViewModel: ViewModel() {
    private val accountService: AccountService = AccountService()

    val tasks: SnapshotStateList<ShoppingList> = mutableStateListOf()

    var inputState: MutableState<String?> = mutableStateOf(null)
    fun logOut(navigateRegisterPage: () -> Unit) {
        FirebaseAuth.getInstance().signOut();
        println("logout successful")
        navigateRegisterPage()
    }
    fun createTask(taskTitle: String) {
        tasks.add(0, ShoppingList(taskTitle))
        inputState.value = null
        accountService.addShoppingListItem(taskTitle) // Add to Firestore
    }
}