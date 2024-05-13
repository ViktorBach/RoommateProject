package com.example.roommateproject.FrontPage.Components

import android.annotation.SuppressLint
import android.widget.CalendarView
import android.widget.ToggleButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.roommateproject.ui.theme.boxLayerGrey

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalenderTab() {
    var date by remember {
        mutableStateOf("")
    }
    // State to control the visibility of the event adding interface
    var isAddingEvent by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(0.87f)
            .padding(start = 60.dp)
            .padding(bottom = 30.dp)
            .clip(shape = RoundedCornerShape(20.dp)),
        containerColor = boxLayerGrey,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AndroidView(factory = { context ->
                CalendarView(context).apply {
                    setOnDateChangeListener { calendarView, year, month, day ->
                        date = "$day - ${month + 1} - $year"
                    }
                }
            })
            // Display the added event text at the bottom
            Text(text = userInput)

            // Show the event adding interface when isAddingEvent is true
            if (isAddingEvent) {
                AddEvent(selectedDate = date, onClose = { isAddingEvent = false })
            } else {
                Text(text = "No events yet")
            }

            // IconButton should be placed within the Column scope
            IconButton(onClick = { isAddingEvent = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Event")
            }
        }
    }
}


var userInput: String = ""

@Composable
fun AddEvent(selectedDate: String, onClose: () -> Unit) {
    var eventText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
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
                addEventToSelectedDate(selectedDate, eventText)
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

private fun addEventToSelectedDate(selectedDate: String, eventText: String): MutableList<String> {
    // Here you can implement the logic to add the event to the selected date
    val listOfEvents: MutableList<String> = mutableListOf()

    if (selectedDate.isNotEmpty()) {
        listOfEvents.add(eventText)
    }
    return listOfEvents
}

