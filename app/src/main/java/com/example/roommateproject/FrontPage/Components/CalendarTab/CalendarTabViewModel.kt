package com.example.roommateproject.FrontPage.Components.CalendarTab

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roommateproject.Services.AccountService
import com.google.firebase.firestore.FirebaseFirestore

class CalendarTabViewModel : ViewModel() {
        private val accountService: AccountService = AccountService()

        private val liveEvents = MutableLiveData<List<AccountService.CalendarData>>()

        val events: LiveData<List<AccountService.CalendarData>> get() = liveEvents

        fun fetchEventsFilteredByDate(date: String) {
            accountService.getCalendarEvents { success, calendarEvents ->
                if (success) {
                    val filteredEvents = calendarEvents.filter { it.date == date }
                    liveEvents.value = filteredEvents
                } else {
                    liveEvents.value = emptyList()
                }
            }
        }

        fun addCalendarEvent(eventText: String) {
            accountService.addCalendarEvent(eventText)
            // Optionally fetch updated events
        }

        fun deleteEventByUid(uid: String) {
            accountService.deleteCalendarEventByUid(uid,
                onSuccess = {
                    liveEvents.value = liveEvents.value?.filter { it.uid != uid }
                },
                onFailure = { exception ->
                    println("Failed to delete calendar event: ${exception.message}")
                }
            )
        }
    }

