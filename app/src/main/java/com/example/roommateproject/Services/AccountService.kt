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
        // Check if the user exists before attempting to sign in
        Firebase.auth.fetchSignInMethodsForEmail(email)
            .addOnSuccessListener { result ->
                if (result.signInMethods!!.contains(EmailAuthProvider.EMAIL_PASSWORD_SIGN_IN_METHOD)) {
                    // User exists, attempt to sign in
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
                } else {
                    // User does not exist, return failure
                    onResult(false, "User does not exist. Please register.")
                }
            }
            .addOnFailureListener { exception ->
                // Error occurred while checking for existing user
                val errorMessage = exception.message
                onResult(false, errorMessage)
            }
    }
}