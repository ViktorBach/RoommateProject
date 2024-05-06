package com.example.roommateproject.FrontPage.Notification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

class NotificationApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        val notificationChannel = NotificationChannel(
            "ImHome_reminder",
            "home reminder channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        notificationChannel.description = "here is the description"

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(notificationChannel)

    }
}