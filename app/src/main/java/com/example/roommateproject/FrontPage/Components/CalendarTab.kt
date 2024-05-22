package com.example.roommateproject.FrontPage.Components

import android.annotation.SuppressLint
import android.widget.CalendarView
import android.widget.ToggleButton
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.Register.RegisterViewModel
import com.example.roommateproject.Services.AccountService
import com.example.roommateproject.ui.theme.boxLayerGrey
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalenderTab() {
    val calendarTapViewModel = viewModel<CalendarTapViewModel>()
    var date by remember {
        mutableStateOf("")
    }
    // State to control the visibility of the event adding interface
    var isAddingEvent by remember { mutableStateOf(false) }

    // TODO: remember state change on the showevents list
    var currentCalendarEvents by remember { mutableStateOf(emptyList<AccountService.CalendarData>()) }

    Box(
        modifier = Modifier
            .fillMaxHeight(0.6f)
            .fillMaxWidth(0.9f)
            .padding(start = 35.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = boxLayerGrey)
            .verticalScroll(ScrollState(1))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // IconButton should be placed within the Column scope
            IconButton(onClick = { isAddingEvent = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Event")
            }
                AndroidView(factory = { context ->
                    CalendarView(context).apply {
                        setOnDateChangeListener { calendarView, year, month, day ->
                            date = "$day - ${month + 1} - $year"
                            AccountService.currentDate = date

                            val eventList = calendarTapViewModel.getEvents() {
                                eventList -> println("calendarEvents:${eventList}")
                               // ShowEvents(eventList)
                                currentCalendarEvents = eventList
                            }

                        }
                    }
                })

            // Show the event adding interface when isAddingEvent is true
            if (isAddingEvent) {
                AddEventComponent(selectedDate = date, onClose = { isAddingEvent = false })
            } else {
                Text(text = date)
            }
            ShowEvents(currentCalendarEvents)
        }
    }
}

@Composable
fun AddEventComponent(selectedDate: String, onClose: () -> Unit) {
    var eventText by remember { mutableStateOf("") }
    val accountService: AccountService = AccountService();

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = eventText,
                onValueChange = { eventText = it },
                label = { Text("Add event:") },
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Button(onClick = {
                // Call function to add event to selected date
                accountService.addCalendarEvent(eventText)
                // Close the event adding interface
                onClose()
            }) {
                Text(text = "Add Event")
            }
            Button(onClick = onClose) {
                Text(text = "Close")
            }
        }
    }
}

@Composable
fun ShowEvents(events : List<AccountService.CalendarData>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = events.toString())
    }
}




