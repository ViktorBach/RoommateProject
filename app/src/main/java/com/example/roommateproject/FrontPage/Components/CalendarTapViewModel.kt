package com.example.roommateproject.FrontPage.Components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roommateproject.Services.AccountService
import kotlinx.coroutines.launch

class CalendarTapViewModel: ViewModel() {
    private val accountService: AccountService = AccountService();

    fun getEvents(onResult: (List<AccountService.CalendarData>) -> Unit) {
        accountService.getCalendarEvents() { success, calendarEvents ->
            println("CalendarTapViewModel-calendarEvents:${calendarEvents}")
            onResult(calendarEvents)
        }
    }

}