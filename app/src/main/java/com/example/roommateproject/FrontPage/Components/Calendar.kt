package com.example.roommateproject.FrontPage.Components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun DropDownCalendar() {
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
            Text("Calendar")
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
            Content()
        }
        if (isExpanded) {
            events.forEach { event ->
                Text("${event.title} - ${event.date}")
            }
        }
    }
}

@Preview
@Composable
fun PreviewDropDownCalendar() {
    DropDownCalendar()
}


data class CalendarEvent(val title: String, val date: String)

fun fetchCalendarEvents(): List<CalendarEvent> {
    // In a real scenario, you would fetch events from Google Calendar API here
    return listOf(
        CalendarEvent("Event 1", "2024-05-01"),
        CalendarEvent("Event 2", "2024-05-05"),
        CalendarEvent("Event 3", "2024-05-10")
    )
}

@Composable
fun Content() {
    LazyRow {
        items(items = List(7) { Pair("Sun", "21") }) { date ->
            ContentItem(date.first, date.second)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentItem(day: String, date: String) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
    ) {
        Column(
            modifier = Modifier
                .width(40.dp)
                .height(48.dp)
                .padding(4.dp)
        ) {
            Text(
                text = day,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = date,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

