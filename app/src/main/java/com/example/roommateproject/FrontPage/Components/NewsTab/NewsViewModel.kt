package com.example.roommateproject.FrontPage.Components.NewsTab

import androidx.lifecycle.ViewModel
import com.example.roommateproject.Services.AccountService
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.Instant
import java.util.Date

/*****************************************************************************/
                            // NewsViewModel class //
/*****************************************************************************/
data class Event(val eventType: String, val timeStamp: Long)

class NewsViewModel : ViewModel() {
    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events

    val accountService: AccountService = AccountService();

    private val db = FirebaseFirestore.getInstance()
    private var listenerRegistration: ListenerRegistration? = null

    init {
        fetchEvents()
    }

    // Function to fetch events from Firestore via document snapshots
    private fun fetchEvents() {
        // calculates the amount of days we would like to fetch events by timestamp
        val oneDayAgo = Timestamp(Date.from(Instant.now().minus(java.time.Duration.ofDays(1))))

        listenerRegistration = db.collection("events")
            .orderBy("timeStamp", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .whereEqualTo("homeId", AccountService.currentHomeId)
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    // Handle error
                    return@addSnapshotListener  // keeps the view up to date
                }
                val eventsList = snapshot?.documents?.mapNotNull { document ->
                    val timeStamp = document.getTimestamp("timeStamp")
                    // Filter out events older than 24 hours
                    if (timeStamp != null && timeStamp > oneDayAgo) {
                        Event(
                            eventType = document.getString("eventText") ?: "",
                            timeStamp = timeStamp.toDate().time
                        )
                    } else {
                        null // Exclude this event
                    }
                } ?: emptyList()

                _events.value = eventsList
            }
    }

    // Clears the listener when the ViewModel is cleared
    override fun onCleared() {
        super.onCleared()
        listenerRegistration?.remove()
    }
}
