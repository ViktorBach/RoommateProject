package com.example.roommateproject

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.roommateproject.FrontPage.Components.UI.CustomDrawer
import com.example.roommateproject.FrontPage.FrontPage
import com.example.roommateproject.Navigation.Navigation
import com.example.roommateproject.ui.theme.RoommateProjectTheme
import createNotificationChannel
import kotlinx.coroutines.launch
import sendNotification

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel(this)  // Call to setup notification channel
        setContent {
            RoommateProjectTheme {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed) // Initialize drawer state
                val coroutineScope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerContent = {
                        CustomDrawer(
                            isOpen = drawerState.isOpen,
                            onClose = { coroutineScope.launch { drawerState.close() } },
                            content = {
                                // Your drawer content here
                                Text(text = "")
                            }
                        )
                    },
                    drawerState = drawerState
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        FrontPage(
                            drawerState = drawerState,
                            navigateRegisterPage = {},
                            function = {})
                        Navigation(drawerState = drawerState)
                    }
                }
            }
        }

        checkNotificationPermission()
    }


    private val PERMISSION_REQUEST_CODE = 123
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, // Change to Array<String>
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, send the notification
                sendNotification("Notification permission granted", this)
            } else {
                // Permission denied, handle accordingly
            }
        }
    }

    private fun checkNotificationPermission() {
        if (!NotificationManagerCompat.from(this).areNotificationsEnabled()) {
            Log.d("MainActivity", "Notifications are not enabled")
        } else if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted, request the permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                PERMISSION_REQUEST_CODE
            )
        } else {
            Log.d("MainActivity", "Notifications are enabled")
        }
    }
}






