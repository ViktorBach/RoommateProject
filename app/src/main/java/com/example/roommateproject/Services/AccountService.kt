package com.example.roommateproject.Services


import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class AccountService {

    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("users")

    fun authenticate(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
        val user = authResult.user
        // Save user data to Fire store
        val userData = hashMapOf(
            "email" to email,
            // You can add more fields as needed
        )
        usersCollection.document(user!!.uid).set(userData)
            .addOnSuccessListener {
                onResult(true, null)
            }
            .addOnFailureListener { exception ->
                onResult(false, exception.message)
            }
    }
    .addOnFailureListener { exception ->
        onResult(false, exception.message)
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