package com.example.roommateproject.FrontPage.Notification


import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.roommateproject.R
import kotlin.random.Random

class HomeNotificationService(
    private val context: Context,
    private val notificationManager: NotificationManager,
    private val resources: android.content.res.Resources
) {
    fun showBasicNotification() {
        requireNotNull(notificationManager) { "NotificationManager is null" }

        val notification = NotificationCompat.Builder(context, "ImHome_reminder")
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("I'm home")
            .setContentText("username arrived home")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }
}

