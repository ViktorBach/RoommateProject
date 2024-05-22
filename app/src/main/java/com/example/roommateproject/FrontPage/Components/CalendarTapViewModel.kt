package com.example.roommateproject.FrontPage.Components

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roommateproject.Services.AccountService

class CalendarTapViewModel: ViewModel() {
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
}