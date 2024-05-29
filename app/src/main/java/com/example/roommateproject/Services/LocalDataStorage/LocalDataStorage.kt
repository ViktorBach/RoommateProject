package com.example.roommateproject.Services.LocalDataStorage

import com.example.roommateproject.Services.LocalDataStorage.EventData

class LocalDataStorage {

    // Sofie
    // Companion object that makes it possible to use these mutable variables across the whole codebase
    companion object {
        var currentEvents: List<EventData> = listOf()
        var currentUserId = ""
        var currentUserName = ""
        var currentHomeId = ""
        var currentCalendarEvents: List<EventData> = listOf()
        var currentDate = ""
    }

}
