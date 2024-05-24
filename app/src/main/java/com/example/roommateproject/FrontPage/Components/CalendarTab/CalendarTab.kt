package com.example.roommateproject.FrontPage.Components

import android.annotation.SuppressLint
import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.FrontPage.Components.CalendarTab.CalendarTabViewModel
import com.example.roommateproject.Services.AccountService
import com.example.roommateproject.ui.theme.boxLayerGrey
import com.example.roommateproject.ui.theme.lightGrey
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarTab() {
    val calendarTapViewModel = viewModel<CalendarTabViewModel>()
    var date by remember { mutableStateOf("") }
    var isAddingEvent by remember { mutableStateOf(false) }
    val events by calendarTapViewModel.events.observeAsState(emptyList())
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    var isScrolledToEvents by remember { mutableStateOf(false) }
    val accountService = AccountService()  // Initialize AccountService


    Box(
        modifier = Modifier
            .fillMaxHeight(0.7f)
            .fillMaxWidth(0.85f)
            .padding(start = 60.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = boxLayerGrey)
            .verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
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
            }, modifier = Modifier
                .width(300.dp)  // Set desired width for the CalendarView
                .height(300.dp)  // Set desired height for the CalendarView
                .padding(bottom = if (events.isEmpty()) 0.dp else 8.dp))  // Add padding to move the arrow button closer

            if (events.isNotEmpty()) {
                IconButton(onClick = {
                    coroutineScope.launch {
                        if (isScrolledToEvents) {
                            scrollState.animateScrollTo(0)
                        } else {
                            scrollState.animateScrollTo(scrollState.maxValue)
                        }
                        isScrolledToEvents = !isScrolledToEvents
                    }
                }) {
                    Icon(
                        imageVector = if (isScrolledToEvents) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "Toggle Events View"
                    )
                }

                ShowEvents(events)
            }
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
            },
            accountService = accountService  // Pass AccountService instance to AddEventDialog
        )
    }
    IconButton(onClick = { isAddingEvent = true }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Event")
    }
}

@Composable
fun AddEventDialog(selectedDate: String, onClose: () -> Unit, onAddEvent: (String) -> Unit,
    accountService: AccountService // Accept AccountService instance
) {
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
                    accountService.addEvent(AccountService.EventType.CALENDAR_EVENT)  // Log the event
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
    val calendarTapViewModel = viewModel<CalendarTabViewModel>()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
            .padding(5.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            events.forEach { event ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(lightGrey)
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = event.eventText,
                        color = Color.Black,
                        lineHeight = 35.sp,
                        modifier = Modifier
                            .background(lightGrey)
                            .weight(1f) // This ensures the text takes up remaining space
                            .padding(8.dp)
                    )
                    IconButton(
                        onClick = {
                            calendarTapViewModel.deleteEvent(event.eventText)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete, modifier = Modifier
                                .background(
                                    lightGrey
                                )
                                .fillMaxSize(0.37f)
                                .align(Alignment.CenterVertically),
                            contentDescription = "Delete calendar event"
                        )
                    }
                }
            }
        }
    }
}


