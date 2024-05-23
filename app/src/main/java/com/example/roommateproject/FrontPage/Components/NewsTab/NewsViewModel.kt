package com.example.roommateproject.FrontPage.Components.NewsTab

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Event(val eventType: String, val timeStamp: Long)

class NewsViewModel : ViewModel() {
    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events

    private val db = FirebaseFirestore.getInstance()
    private var listenerRegistration: ListenerRegistration? = null

    init {
        fetchEvents()
    }

    private fun fetchEvents() {
        listenerRegistration = db.collection("events")
            .orderBy("timeStamp", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    // Handle error
                    return@addSnapshotListener
                }

                val eventsList = snapshot?.documents?.map { document ->
                    val timeStamp = document.getTimestamp("timeStamp") ?: Timestamp.now()
                    Event(
                        eventType = document.getString("eventText") ?: "",
                        timeStamp = timeStamp.toDate().time
                    )
                } ?: emptyList()

                _events.value = eventsList
            }
    }

    override fun onCleared() {
        super.onCleared()
        listenerRegistration?.remove()
    }
}