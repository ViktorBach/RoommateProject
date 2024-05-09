package com.example.roommateproject.FrontPage.Components

import com.example.roommateproject.FrontPage.Components.CalendarData.CalendarUiModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class CalendarUiModel(
    val selectedDate: CalendarUiModel.Date, // the date selected by the User. by default is Today.
    val visibleDates: List<CalendarUiModel.Date> // the dates shown on the screen
) {
    val startDate: CalendarUiModel.Date = visibleDates.first() // the first of the visible dates
    val endDate: CalendarUiModel.Date = visibleDates.last() // the last of the visible dates

    data class Date(
        val date: LocalDate,
        val isSelected: Boolean,
        val isToday: Boolean
    ) {
        val day: String = date.format(DateTimeFormatter.ofPattern("E")) // get the day by formatting the date
    }
}