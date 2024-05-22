package com.example.roommateproject.FrontPage.Components

import android.annotation.SuppressLint
import android.widget.CalendarView
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.roommateproject.Services.AccountService
import com.example.roommateproject.ui.theme.boxLayerGrey
import com.example.roommateproject.ui.theme.earthyBrown


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalenderTab() {
    val calendarTapViewModel = viewModel<CalendarTapViewModel>()

    // ObserveAsState observes the ViewModels state and updates the UI so it fits the state
    val events by calendarTapViewModel.events.observeAsState(emptyList())
    var date by remember { mutableStateOf("") }
    var isAddingEvent by remember { mutableStateOf(false) }

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
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(onClick = { isAddingEvent = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Event")
            }
            AndroidView(factory = { context ->
                CalendarView(context).apply {
                    setOnDateChangeListener { _, year, month, day ->
                        date = "$day - ${month + 1} - $year"
                        AccountService.currentDate = date
                        calendarTapViewModel.fetchEventsFilteredByDate(date)
                    }
                }
            })
            if (isAddingEvent) {
                AddEventComponent(selectedDate = date, onClose = { isAddingEvent = false })
            } else {
                Text(text = date)
            }
            ShowEvents(events)
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
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            events.forEach { event ->
                Text(text = "${event.eventText}", color = earthyBrown)
            }
        }
    }
}




