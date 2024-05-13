package com.example.roommateproject.FrontPage.Components

import android.view.View.OnClickListener
import android.widget.GridLayout
import android.widget.GridView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.data.UiToolingDataApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roommateproject.FrontPage.Components.CalendarData.CalendarDataSource
import com.example.roommateproject.FrontPage.Components.CalendarData.CalendarUiModel
import com.example.roommateproject.SharedComponents.Header
import com.example.roommateproject.ui.theme.boxLayerGrey
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


/*
@Composable
fun DropDownCalendar(modifier: Modifier = Modifier) {
    val dataSource = CalendarDataSource()
    // get the CalendarUiModel from CalendarDataSource, and the lastSelectedDate is Today.
    val calendarUiModel = dataSource.getData(lastSelectedDate = dataSource.today)

    var isExpanded by remember { mutableStateOf(false) }
    var events by remember { mutableStateOf(emptyList<CalendarEvent>()) }

    LaunchedEffect(Unit) {
        events = fetchCalendarEvents()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                // show Today if user selects today date
                // else, show the full format of the days
                text = "Today",
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
            IconButton(onClick = { isExpanded = !isExpanded }) {
                val icon = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.ArrowDropDown
                Icon(icon, contentDescription = "Expand/Collapse")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Content(data = calendarUiModel)
        }
        if (isExpanded) {
            events.forEach { event ->
                Text("${event.title} - ${event.date}")
            }
        }
    }
}

 */
@Composable
fun CalendarApp(modifier: Modifier = Modifier) {
    val dataSource = CalendarDataSource()

    var calenderUiModel by remember { mutableStateOf(dataSource.getData(lastSelectedDate = dataSource.today)) }
    var isExpanded by remember { mutableStateOf(false) }
    var events by remember { mutableStateOf(emptyList<CalendarEvent>()) }

    LaunchedEffect(Unit) {
        events = fetchCalendarEvents()
    }
    Box(modifier = Modifier
        .fillMaxWidth(0.88f)
        .fillMaxHeight(0.6f)
        .padding(start = 50.dp)
        .clip(RoundedCornerShape(10)),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = modifier
                .background(boxLayerGrey)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(
                data = calenderUiModel,
                isExpanded = isExpanded,
                onToggleExpanded = { isExpanded = !isExpanded },
                onPrevClickListener = { startDate ->
                    val finalStartDate = startDate.minusDays(1)
                    calenderUiModel = dataSource.getData(
                        startDate = finalStartDate,
                        lastSelectedDate = calenderUiModel.selectedDate.date
                    )
                },
                onNextClickListener = { endDate ->
                    val finalStartDate = endDate.plusDays(2)
                    calenderUiModel = dataSource.getData(
                        startDate = finalStartDate,
                        lastSelectedDate = calenderUiModel.selectedDate.date
                    )
                }
            )
            if (isExpanded) {
                Content(data = calenderUiModel, onDateClickListener = { date ->
                    calenderUiModel = calenderUiModel.copy(
                        selectedDate = date,
                        visibleDates = calenderUiModel.visibleDates.map {
                            it.copy(
                                isSelected = it.date.isEqual(date.date)
                            )
                        }
                    )
                })
            } else {
                // Show only one week of dates
                Content(
                    data = calenderUiModel.copy(
                        visibleDates = calenderUiModel.visibleDates.take(
                            7
                        )
                    ), onDateClickListener = { date ->
                        calenderUiModel = calenderUiModel.copy(
                            selectedDate = date,
                            visibleDates = calenderUiModel.visibleDates.map {
                                it.copy(
                                    isSelected = it.date.isEqual(date.date)
                                )
                            }
                        )
                    })
            }
        }
    }
}


@Composable
fun Header(
    data: CalendarUiModel,
    isExpanded: Boolean,
    onToggleExpanded: () -> Unit,
    onPrevClickListener: (LocalDate) -> Unit,
    onNextClickListener: (LocalDate) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth(0.5f)
    ) {
        Text(
            text = if (data.selectedDate.isToday) {
                "Today"
            } else {
                data.selectedDate.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))
            },
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        IconButton(onClick = onToggleExpanded) {
            val icon = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.ArrowDropDown
            Icon(icon, contentDescription = "Expand/Collapse")
        }
        IconButton(onClick = {
            onPrevClickListener(data.startDate.date)
        }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
        }
        IconButton(onClick = {
            onNextClickListener(data.endDate.date)
        }) {
            Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "Next")
        }
    }
    Row(modifier = Modifier
        .background(Color.Gray)
        .fillMaxWidth(0.96f)
        .fillMaxHeight(0.005f)
    ) {

    }
}



@Composable
fun Content(data: CalendarUiModel, onDateClickListener: (CalendarUiModel.Date) -> Unit) {
    Column(modifier = Modifier
        .fillMaxHeight(0.1f)
        .fillMaxWidth(0.95f)
        .padding(start = 16.dp, top = 5.dp)
    ){
        if (data.isExpanded) {
            // Show the month as a grid layout
            MonthView(data = data, onDateClickListener = onDateClickListener)
        } else {
            // Show only one week of dates
            LazyRow {
                items(data.visibleDates) { date ->
                    ContentItem(
                        date = date,
                        onDateClickListener = onDateClickListener
                    )
                }
            }
        }
    }
}

@Composable
fun MonthView(data: CalendarUiModel, onDateClickListener: (CalendarUiModel.Date) -> Unit) {
    Column {
        repeat(6) { rowIndex ->
            Row(
            ) {
                repeat(7) { colIndex ->
                    val dateIndex = rowIndex * 7 + colIndex
                    if (dateIndex < data.visibleDates.size) {
                        val date = data.visibleDates[dateIndex]
                        ContentItem(
                            date = date,
                            onDateClickListener = onDateClickListener
                        )
                    } else {
                        // Add empty space if no date available for this cell
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentItem(date: CalendarUiModel.Date,
                // still, callback should be registered from outside
                onDateClickListener: (CalendarUiModel.Date) -> Unit) {

    Card(
        modifier = Modifier
            .padding(vertical = 3.dp, horizontal = 3.dp)
            .clickable { // making the element clickable, by adding 'clickable' modifier
                onDateClickListener(date)
            },

        colors = CardDefaults.cardColors(
            containerColor = if (date.isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.secondary
            }
        )
    ) {
        Column(
            modifier = Modifier
                .width(30.dp)
                .height(38.dp)
                .padding(3.dp)
        ) {
            Text(
                text = date.day,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = date.date.dayOfMonth.toString(),
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

data class CalendarEvent(val title: String, val date: String)

fun fetchCalendarEvents(): MutableList<CalendarEvent> {
    // In a real scenario, you would fetch events from Google Calendar API here
    return mutableListOf(
        CalendarEvent("Event 1", "2024-05-01"),
        CalendarEvent("Event 2", "2024-05-05"),
        CalendarEvent("Event 3", "2024-05-10")
    )
}

@Preview
@Composable
fun PreviewDropDownCalendar() {
    CalendarApp()
}

fun addCalendarEvent() {

}

