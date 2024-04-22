package com.example.roommateproject.Services


import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class AccountService {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("users")
    private val homesCollection = db.collection("homes")

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

    fun homeLogin(name: String, password: String, usernames: List<String>, onResult: (Boolean, String?) -> Unit) {
        homesCollection.whereEqualTo("home", name).get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.isEmpty) {
                    onResult(false, "Household not found")
                } else {
                    val homeDocument = querySnapshot.documents.first()
                    val storedPassword = homeDocument.getString("password")
                    if (storedPassword == password) {
                        val userIDs = mutableListOf<String>()
                        var completedTasks = 0
                        val totalTasks = usernames.size

                        for (username in usernames) {
                            usersCollection.whereEqualTo("username", username).get()
                                .addOnSuccessListener { userQuerySnapshot ->
                                    if (userQuerySnapshot.isEmpty) {
                                        onResult(false, "User '$username' not found")
                                    } else {
                                        val userDocument = userQuerySnapshot.documents.first()
                                        val userId = userDocument.id
                                        userIDs.add(userId)
                                    }
                                    completedTasks++
                                    if (completedTasks == totalTasks) {
                                        // All tasks completed, update the home document
                                        val homeData = hashMapOf(
                                            "name" to name,
                                            "members" to userIDs
                                        )
                                        homeDocument.reference.set(homeData)
                                            .addOnSuccessListener {
                                                onResult(true, null)
                                            }
                                            .addOnFailureListener { exception ->
                                                onResult(false, exception.message)
                                            }
                                    }
                                }
                                .addOnFailureListener { exception ->
                                    onResult(false, exception.message)
                                }
                        }
                    } else {
                        onResult(false, "Incorrect password")
                    }
                }
            }
            .addOnFailureListener { exception ->
                onResult(false, exception.message)
            }
    }
    fun createNewHouse(name: String, password: String, usernames: List<String>, onResult: (Boolean, String?) -> Unit) {
        val userIds = mutableListOf<String>()
        var completedTasks = 0
        val totalTasks = usernames.size

        // Iterate over the usernames and get their corresponding document IDs
        for (username in usernames) {
            usersCollection.whereEqualTo("username", username).get()
                .addOnSuccessListener { querySnapshot ->
                    if (querySnapshot.isEmpty) {
                        onResult(false, "User '$username' not found") // User not found
                    } else {
                        val userDocument = querySnapshot.documents.first()
                        userIds.add(userDocument.id) // Add document ID to the list
                    }

                    completedTasks++
                    if (completedTasks == totalTasks) {
                        // If all tasks are completed, create the new household
                        val homeData = hashMapOf(
                            "home" to name,
                            "password" to password,
                            "members" to userIds
                        )

                        homesCollection.add(homeData) // Add new household to Firestore
                            .addOnSuccessListener {
                                onResult(true, null) // Household added successfully
                            }
                            .addOnFailureListener { exception ->
                                onResult(false, exception.message) // Handle errors
                            }
                    }
                }
                .addOnFailureListener { exception ->
                    onResult(false, exception.message) // Handle query error
                }
        }
    }



}