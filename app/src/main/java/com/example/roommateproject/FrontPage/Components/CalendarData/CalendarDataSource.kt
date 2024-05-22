package com.example.roommateproject.FrontPage.Components.CalendarData

import java.time.LocalDate

class CalendarDataSource {

    val today: LocalDate
        get() = LocalDate.now()

    fun getData(startDate: LocalDate = today, lastSelectedDate: LocalDate): CalendarUiModel {
        val firstDayOfMonth = startDate.withDayOfMonth(1)
        val lastDayOfMonth = firstDayOfMonth.plusMonths(1).minusDays(1)
        val visibleDates = getDatesBetween(firstDayOfMonth, lastDayOfMonth)
        return toUiModel(visibleDates, lastSelectedDate)
    }

    fun getMonthData(startDate: LocalDate = today, lastSelectedDate: LocalDate): CalendarUiModel {
        val firstDayOfMonth = startDate.withDayOfMonth(1)
        val lastDayOfMonth = firstDayOfMonth.plusMonths(1).minusDays(1)
        val visibleDates = getDatesBetween(firstDayOfMonth, lastDayOfMonth)
        return toUiModel(visibleDates, lastSelectedDate)
    }

    private fun getDatesBetween(startDate: LocalDate, endDate: LocalDate): List<LocalDate> {
        return generateSequence(startDate) { date ->
            date.plusDays(1)
        }
            .takeWhile { it.isBefore(endDate) || it.isEqual(endDate) }
            .toList()
    }

    private fun toUiModel(dateList: List<LocalDate>, lastSelectedDate: LocalDate): CalendarUiModel {
        return CalendarUiModel(
            selectedDate = toItemUiModel(lastSelectedDate, true),
            visibleDates = dateList.map {
                toItemUiModel(it, it.isEqual(lastSelectedDate))
            },
        )
    }

    private fun toItemUiModel(date: LocalDate, isSelectedDate: Boolean) = CalendarUiModel.Date(
        isSelected = isSelectedDate,
        isToday = date.isEqual(today),
        date = date,
    )
}
