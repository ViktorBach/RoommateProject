package com.example.roommateproject.Services


import com.example.roommateproject.FrontPage.Components.ListView.ShoppingList
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

/*****************************************************************************/
// AccountService class is responsible for handling user authentication and data //
  /*****************************************************************************/

class AccountService {

    // Initialize Firebase Auth
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
        var currentHomeId = ""
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

      // Data class that represents an event in the application
    data class EventData(
        var eventType: String,
        var timeStamp: String
    )

      // Function that adds an event to Firestore
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

      // Function that requests to get shopping list data from firestore collection
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

      // Function that requests to get shopping list data from firestore collection
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

      // Function that requests to get shopping list data from firestore collection
    fun removeShoppingListItem(taskTitle: String, onResult: (Boolean) -> Unit) {
        val documentReference = shoppingListCollection.document(currentHomeId)

        documentReference.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // Use FieldValue.delete() to remove the field
                    val updates = mapOf<String, Any?>(taskTitle to FieldValue.delete())
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

      // Function that requests to get shopping list data from firestore collection
    fun updateShoppingListItem(taskTitle: String, isCompleted: Boolean) {
        val documentReference = shoppingListCollection.document(currentHomeId)

        documentReference.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    documentReference.update(mapOf("$taskTitle.completed" to isCompleted))
                        .addOnSuccessListener {
                            println("Item updated successfully")
                        }
                        .addOnFailureListener { e ->
                            println("Error updating item: ${e.message}")
                        }
                }
            }
            .addOnFailureListener { e ->
                println("Error retrieving document: ${e.message}")
            }
    }

      // Data class that represents a calendar event in the application
    data class CalendarData(
        var eventText: String,
        var date: String,
        var uid: String = ""
    )

      // Function that adds a calendar event to Firestore
    fun addCalendarEvent(event: String) {
        val newEventRef = calendarCollection.document()
        val calendarData = hashMapOf(
            "eventText" to event,
            "homeId" to currentHomeId,
            "date" to currentDate,
            "uid" to newEventRef.id
        )
        newEventRef.set(calendarData)
            .addOnSuccessListener {
                println("Calendar event added successfully")
            }
            .addOnFailureListener { exception ->
                println("Error adding calendar event: ${exception.message}")
            }
    }

    // Function that requests to get calendar event data from firestore collection
    fun getCalendarEvents(onResult: (Boolean, List<CalendarData>) -> Unit)  {
         calendarCollection
            .whereEqualTo("homeId", AccountService.currentHomeId)
            .get().addOnSuccessListener { result ->
                 val eventList = result.map { doc ->
                 println("calendarDoc:" + doc)
                 CalendarData(
                     uid = doc.id,  // Use the document ID as the uid
                     eventText = doc.getString("eventText") ?: "",
                     date = doc.getString("date") ?: ""
                 )}.toList()
                 onResult(true, eventList)
             }
    }

      // Function that deletes calendar event data from firestore collection
    fun deleteCalendarEventByUid(uid: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        calendarCollection.document(uid).delete()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }


    // Function that requests to get news event data from firestore collection
    suspend fun getEvents() {
        // calculates the amount of days we would like to fetch events by timestamp
        val oneDay = Instant.now().minus(java.time.Duration.ofDays(1)) // 24 hours ago
        // sets the currentEvent companion object to the firestore eventsCollection
        currentEvents = eventsCollection
            .whereEqualTo("homeId", AccountService.currentHomeId)
            .whereGreaterThan("timeStamp", Timestamp(Date.from(oneDay)))
            .get().await()
            .map { doc ->
                val timeStamp = doc.get("timeStamp")
                val dateTime = when (timeStamp) {
                    is Timestamp -> timeStamp.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                    is String -> LocalDateTime.parse(timeStamp, DateTimeFormatter.ISO_DATE_TIME)
                    else -> null
                }

                val formatter = DateTimeFormatter.ofPattern("EEEE HH:mm") // Defines the date format
                val formattedDate = dateTime?.format(formatter) ?: "Unknown date"

                EventData(
                    eventType = doc.getString("eventText") ?: "Unknown event", // Fetching the event text directly
                    timeStamp = formattedDate
                )
            }.toList()
    }



    // Function that registers a user with email and password
    fun authenticate(email: String, password: String, username: String, onResult: (Boolean, String?) -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
        val user = authResult.user
        // Save user data to Fire store
        val userData = hashMapOf(
            "email" to email,
            "username" to username
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
    // Function that logs in a user with email and password
     fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        // Attempt to sign in the user
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
            // Login successful
            val user = authResult.user
            // Get and set the current features to the current logged in user:
            usersCollection.document(user!!.uid).get()
                .addOnSuccessListener { document ->
                    val username = document.getString("username").toString()
                    currentUserName = document.getString("username").toString()
                    currentUserId = user.uid
                    currentHomeId = document.getString("homeId").toString()

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

    // Function that sends a password reset email to the user
    fun sendPasswordResetEmail(email: String, onResult: (Boolean, String?) -> Unit) {
        auth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                onResult(true, null)
            }
            .addOnFailureListener { exception ->
                onResult(false, exception.message)
            }
    }

    // Function that logs in a home with name and password
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
    // Function that creates a new home
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
                                userDocs.forEach{doc ->
                                    // sending the home id generated to the user document connected to the home id
                                    AccountService.currentHomeId = newHomeDoc.id
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
