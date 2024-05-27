package com.example.roommateproject.FrontPage.Components.NewsTab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roommateproject.Services.AccountService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date
import kotlin.time.Duration

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

    private fun fetchEvents() {
        val oneDayAgo = Timestamp(Date.from(Instant.now().minus(java.time.Duration.ofDays(1)))) // Calculate the timestamp for 24 hours ago

        listenerRegistration = db.collection("events")
            .orderBy("timeStamp", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .whereEqualTo("homeId", AccountService.currentHomeId)
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    // Handle error
                    return@addSnapshotListener
                }
                println("Tim: ${AccountService.currentHomeId}")
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


    override fun onCleared() {
        super.onCleared()
        listenerRegistration?.remove()
    }
}