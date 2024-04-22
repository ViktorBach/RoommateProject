package com.example.roommateproject.Services


import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class AccountService {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("users")
    private val homeCollection = db.collection("homes")

    fun authenticate(email: String, password: String, username: String, onResult: (Boolean, String?) -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
        val user = authResult.user
        // Save user data to Fire store
        val userData = hashMapOf(
            "email" to email,
            "username" to username
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
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                // Login successful
                val user = authResult.user
                // Retrieve the username associated with the logged-in user
                usersCollection.document(user!!.uid).get()
                    .addOnSuccessListener { document ->
                        val username = document.getString("username")
                        // Pass the username along with the login result
                        onResult(true, username)
                    }
                    .addOnFailureListener { exception ->
                        onResult(false, exception.message)
                    }
            }
            .addOnFailureListener { exception ->
                // Login failed
                val errorMessage = exception.message
                onResult(false, errorMessage)
            }

    }

    fun home(name: String, password: String, onResult: (Boolean, String?) -> Unit) {

    }
}