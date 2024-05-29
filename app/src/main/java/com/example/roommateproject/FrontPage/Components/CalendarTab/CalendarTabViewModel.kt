package com.example.roommateproject.FrontPage.Components.CalendarTab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roommateproject.Services.AccountService
import com.example.roommateproject.Services.LocalDataStorage.CalendarData

/*****************************************************************************/
// CalendarTabViewModel class //

/*****************************************************************************/

// Sofie
class CalendarTabViewModel : ViewModel() {
    private val accountService: AccountService = AccountService()

    private val liveEvents = MutableLiveData<List<CalendarData>>()

    val events: LiveData<List<CalendarData>> get() = liveEvents

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

    fun addEventToCalendar(eventText: String) {
        accountService.addCalendarEvent(eventText)
    }

    fun deleteEventByUid(uid: String) {
        accountService.deleteCalendarEventByUid(
            uid,
            onSuccess = {
                liveEvents.value = liveEvents.value?.filter { it.uid != uid }
                println("Calendar event deleted successfully.")
            },
            onFailure = { exception ->
                println("Failed to delete calendar event: ${exception.message}")
            },
        )
    }
}

