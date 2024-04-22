package com.example.roommateproject.FrontPage

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class FrontPageViewModel: ViewModel() {
    fun logOut(navigateRegisterPage: () -> Unit) {
        FirebaseAuth.getInstance().signOut();
        println("logout successful")
        navigateRegisterPage()
    }
}