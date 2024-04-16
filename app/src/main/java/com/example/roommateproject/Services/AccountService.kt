package com.example.roommateproject.Services


import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.auth

class AccountService {
    fun authenticate(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // User registration successful
                onResult(true, null)
            }
            .addOnFailureListener { exception ->
                // User registration failed
                val errorMessage = exception.message
                onResult(false, errorMessage)
            }
    }

    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        // Attempt to sign in the user
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // Login successful
                onResult(true, null)
            }
            .addOnFailureListener { exception ->
                // Login failed
                val errorMessage = exception.message
                onResult(false, errorMessage)
            }
    }
}