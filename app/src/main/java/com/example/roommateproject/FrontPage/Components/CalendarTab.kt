package com.example.roommateproject.FrontPage.Components

import android.annotation.SuppressLint
import android.widget.CalendarView
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.Services.AccountService
import com.example.roommateproject.ui.theme.boxLayerGrey
import org.intellij.lang.annotations.JdkConstants.VerticalScrollBarPolicy

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarTab() {
    val calendarTapViewModel = viewModel<CalendarTabViewModel>()
    var date by remember { mutableStateOf("") }
    var isAddingEvent by remember { mutableStateOf(false) }
    val events by calendarTapViewModel.events.observeAsState(emptyList())

    Box(
        modifier = Modifier
            .fillMaxHeight(0.7f)
            .fillMaxWidth(0.85f)
            .padding(start = 60.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = boxLayerGrey)
            .verticalScroll(ScrollState(1))
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
                AndroidView(factory = { context ->
                    CalendarView(context).apply {
                        setOnDateChangeListener { _, year, month, day ->
                            date = "$day - ${month + 1} - $year"
                            AccountService.currentDate = date

                            calendarTapViewModel.fetchEventsFilteredByDate(date)
                        }
                    }
                }, modifier = Modifier.fillMaxSize(0.9f).fillMaxWidth())
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Add Event")
            }
                ShowEvents(events)
        }
    }

    if (isAddingEvent) {
        AddEventDialog(
            selectedDate = date,
            onClose = { isAddingEvent = false },
            onAddEvent = { eventText ->
                calendarTapViewModel.addCalendarEvent(eventText)
                isAddingEvent = false
                // Fetch updated events for the selected date after adding the new one
                calendarTapViewModel.fetchEventsFilteredByDate(date)
            }
        )
    }
    IconButton(onClick = { isAddingEvent = true }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Event")
    }

}

@Composable
fun AddEventDialog(selectedDate: String, onClose: () -> Unit, onAddEvent: (String) -> Unit) {
    var eventText by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onClose() },
        title = { Text(text = "Add Event") },
        text = {
            Column {
                Text(text = if (selectedDate.isEmpty()) "Select a date first" else "Date: $selectedDate")
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = eventText,
                    onValueChange = { eventText = it },
                    label = { Text("Event Description") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onAddEvent(eventText)
                    onClose()
                }
            ) {
                Text("Add Event")
            }
        },
        dismissButton = {
            Button(onClick = { onClose() }) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun ShowEvents(events: List<AccountService.CalendarData>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(5.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            events.forEach { event ->
                Text(text = event.eventText, color = Color.Black)
            }
        }
    }
}
