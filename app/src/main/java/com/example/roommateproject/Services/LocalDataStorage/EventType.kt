package com.example.roommateproject.Services.LocalDataStorage

// Sofie + Natazja
// enum class that contains the internal name and value of the EventTypes we have predefined
enum class EventType(val eventText: String) {
    I_AM_HOME("${LocalDataStorage.currentUserName} is home"),
    I_AM_SLEEPING("${LocalDataStorage.currentUserName} is sleeping"),
    I_AM_WORKING_LATE("${LocalDataStorage.currentUserName} is working late"),
    I_AM_LEAVING("${LocalDataStorage.currentUserName} is leaving"),
    ADD_TO_LIST("${LocalDataStorage.currentUserName} added groceries"),
    GUEST_VISIT("${LocalDataStorage.currentUserName} has a guest over"),
    EARLY_MORNING("${LocalDataStorage.currentUserName} has an early morning"),
    CALENDAR_EVENT("${LocalDataStorage.currentUserName} added a calendar event"),
}
