package com.example.roommateproject.Services


import com.example.roommateproject.FrontPage.Components.ListView.ShoppingList
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import java.time.Instant
import java.time.ZoneId
import java.util.Date


class AccountService {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("users")
    private val homesCollection = db.collection("homes")
    private val eventsCollection = db.collection("events")
    private val calendarCollection = db.collection("calendars")
    private val shoppingListCollection = db.collection("shoppinglist")

    companion object  {
        var currentEvents: List<EventData> = listOf()
        var currentUserId = ""
        var currentUserName = ""
        var currentHomeId = "udl6GrFbM5eMcgJc027j"
        var currentCalendarEvents: List<CalendarData> = listOf()
        var currentDate = ""
    }

    // enum class that contains the internal name and value of the EventTypes we have predefined
    enum class EventType(val eventText: String) {
        I_AM_HOME("${AccountService.currentUserName} is home"),
        I_AM_SLEEPING("${AccountService.currentUserName} is sleeping"),
        I_AM_WORKING_LATE("${AccountService.currentUserName} is working late"),
        I_AM_LEAVING("${AccountService.currentUserName} is leaving"),
        ADD_TO_LIST("${AccountService.currentUserName} added groceries"),
        GUEST_VISIT("${AccountService.currentUserName} has a guest over"),
        EARLY_MORNING("${AccountService.currentUserName} has an early morning"),
        CALENDAR_EVENT("${AccountService.currentUserName} added a calendar event")
    }

    data class EventData(
        var eventType: String,
        var timeStamp: String
    )

    fun addEvent(eventType: EventType) {
        val eventData = hashMapOf(
            "eventName" to eventType.name, // Store the name of the enum
            "eventText" to eventType.eventText, // Store the event text
            "homeId" to currentHomeId,
            "timeStamp" to Timestamp.now(),
            "userId" to currentUserId
        )

        eventsCollection.add(eventData) // Adds new event to Firestore
    }

    fun addShoppingListItem(taskTitle: String) {
        val itemData = hashMapOf(
            "title" to taskTitle,
            "completed" to false,
            "createdAt" to Timestamp.now()
        )

        val documentReference = shoppingListCollection.document(currentHomeId)

        documentReference.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    documentReference.update(mapOf(taskTitle to itemData))
                        .addOnSuccessListener {
                            println("Item added successfully")
                        }
                        .addOnFailureListener { e ->
                            println("Error adding item: ${e.message}")
                        }
                } else {
                    documentReference.set(mapOf(taskTitle to itemData))
                        .addOnSuccessListener {
                            println("Item added successfully")
                        }
                        .addOnFailureListener { e ->
                            println("Error adding item: ${e.message}")
                        }
                }
            }
            .addOnFailureListener { e ->
                println("Error retrieving document: ${e.message}")
            }
    }

    fun getShoppingListItems(onResult: (Boolean, List<ShoppingList>) -> Unit) {
        shoppingListCollection.document(currentHomeId).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val items = document.data?.entries?.mapNotNull { entry ->
                        val title = entry.key
                        val data = entry.value as? Map<*, *> ?: return@mapNotNull null
                        val completed = data["completed"] as? Boolean ?: false
                        ShoppingList(title.toString(), completed)
                    } ?: emptyList()
                    onResult(true, items)
                } else {
                    onResult(true, emptyList())
                }
            }
            .addOnFailureListener { e ->
                println("Error retrieving shopping list items: ${e.message}")
                onResult(false, emptyList())
            }
    }

    fun removeShoppingListItem(taskTitle: String, onResult: (Boolean) -> Unit) {
        val documentReference = shoppingListCollection.document(currentHomeId)

        documentReference.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val updates = mapOf<String, Any?>(taskTitle to FieldValue.delete()) // Use FieldValue.delete() to remove the field
                    documentReference.update(updates)
                        .addOnSuccessListener {
                            println("Item removed successfully")
                            onResult(true)
                        }
                        .addOnFailureListener { e ->
                            println("Error removing item: ${e.message}")
                            onResult(false)
                        }
                } else {
                    onResult(false)
                }
            }
            .addOnFailureListener { e ->
                println("Error retrieving document: ${e.message}")
                onResult(false)
            }
    }


    data class CalendarData(
        var eventText: String,
        var date: String
    )

    fun addCalendarEvent(event: String) {
        val calendarData = hashMapOf(
            "eventText" to event,
            "homeId" to currentHomeId,
            "date" to currentDate,
        )

        calendarCollection.add(calendarData) // Adds new event to Firestore

    }

    // Function that requests to get calendar event data from firestore collection
    fun getCalendarEvents(onResult: (Boolean, List<CalendarData>) -> Unit)  {
         calendarCollection
            .whereEqualTo("homeId", AccountService.currentHomeId)
            .get().addOnSuccessListener { result ->
                 val eventList = result.map { doc ->
                 println("calendarDoc:" + doc)
                 CalendarData(
                     doc.data["eventText"].toString(),
                     doc.data["date"].toString()
                 )}.toList()
                 onResult(true, eventList)
             }
    }

    // Function that requests to get news event data from firestore collection
    // Function that requests to get news event data from firestore collection
    suspend fun getEvents() {
        println("AccountService.currentHomeId: $currentHomeId")
        val oneDay = Instant.now().minus(java.time.Duration.ofDays(1)) // 24 hours ago
        currentEvents = eventsCollection
            .whereEqualTo("homeId", currentHomeId)
            .whereGreaterThan("timeStamp", Timestamp(Date.from(oneDay)))
            .get().await()
            .map { doc ->
                println("DOC: $doc")

                val timeStamp = doc.get("timeStamp")
                val dateTime = when (timeStamp) {
                    is Timestamp -> timeStamp.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                    is String -> LocalDateTime.parse(timeStamp, DateTimeFormatter.ISO_DATE_TIME)
                    else -> null
                }

                val formatter = DateTimeFormatter.ofPattern("EEEE HH:mm") // Define your desired date format here
                val formattedDate = dateTime?.format(formatter) ?: "Unknown date"

                EventData(
                    eventType = doc.getString("eventText") ?: "Unknown event", // Fetch the event text directly
                    timeStamp = formattedDate
                )
            }.toList()
    }




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

     suspend fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        // Attempt to sign in the user
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
            // Login successful
            val user = authResult.user
            // Retrieve the username associated with the logged-in user
            usersCollection.document(user!!.uid).get()
                .addOnSuccessListener { document ->
                    val username = document.getString("username").toString()
                    currentUserName = document.getString("username").toString()
                    currentUserId = user.uid
                    currentHomeId = document.getString("homeId").toString()
                    println(currentHomeId)

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
                                            "members" to userIDs,
                                            "password" to password
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
        val userDocs = mutableListOf<DocumentSnapshot>()

        // Iterate over the usernames and get their corresponding document IDs
        for (username in usernames) {
            usersCollection.whereEqualTo("username", username).get()
                .addOnSuccessListener { querySnapshot ->
                    if (querySnapshot.isEmpty) {
                        onResult(false, "User '$username' not found") // User not found
                    } else {
                        val userDocument = querySnapshot.documents.first()
                        userIds.add(userDocument.id) // Add document ID to the list
                        userDocs.add(userDocument)
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
                            .addOnSuccessListener { newHomeDoc ->
                                userDocs.forEach{doc ->                 // sending the home id generated to the user document connected to the home id
                                    doc.reference.update("homeId", newHomeDoc.id)
                                }
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