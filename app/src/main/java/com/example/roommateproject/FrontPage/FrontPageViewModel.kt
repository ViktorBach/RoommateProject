package com.example.roommateproject.FrontPage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.roommateproject.FrontPage.Components.ListView.ShoppingList
import com.example.roommateproject.Services.AccountService
import com.google.firebase.auth.FirebaseAuth

/*****************************************************************************/
// FrontPageViewModel class for the front page of the app //

/*****************************************************************************/
//Viktor
class FrontPageViewModel : ViewModel() {
    private val accountService: AccountService = AccountService()

    val tasks: SnapshotStateList<ShoppingList> = mutableStateListOf()

    var inputState: MutableState<String?> = mutableStateOf(null)

    // Function that logs out the user and navigates to the register page
    fun logOut(navigateRegisterPage: () -> Unit) {
        FirebaseAuth.getInstance().signOut()
        println("logout successful")
        navigateRegisterPage()
    }

    // Function that creates a new task and adds it to the list
    fun createTask(taskTitle: String) {
        tasks.add(0, ShoppingList(taskTitle))
        inputState.value = null
        accountService.addShoppingListItem(taskTitle) // Add to Firestore
    }
}
