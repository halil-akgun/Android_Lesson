package com.android_lesson

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class TNotificationService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val title = message.notification?.title
        val body = message.notification?.body

        Log.d("Notification", "Title: $title")
        Log.d("Notification", "Body: $body")

        createNotification(title, body)
    }

    fun createNotification(title: String?, body: String?) {
        val builder: NotificationCompat.Builder

        val notificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(
                this,
                1,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

        // version check
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channelId = "channelId"
            val channelName = "Channel Name"
            val channelDescription = "Channel Description"
            val channelImportance = NotificationManager.IMPORTANCE_HIGH

            val channel: NotificationChannel? =
                notificationManager.getNotificationChannel(channelId)

            if (channel == null) {
                // way 1
                notificationManager.createNotificationChannel(
                    NotificationChannel(
                        channelId,
                        channelName,
                        channelImportance
                    ).apply {
                        description = channelDescription
                    }
                )
                // way 2
//                    channel = NotificationChannel(
//                        channelId,
//                        channelName,
//                        channelImportance
//                    )
//                    channel.description = channelDescription
//                    notificationManager.createNotificationChannel(channel)
            }

            builder = NotificationCompat.Builder(this, channelId)
            builder.setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.baseline_cruelty_free_24)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true) // When the user clicks on the notification, it will be canceled.
        } else {
            builder = NotificationCompat.Builder(this)
            builder.setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.baseline_cruelty_free_24)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true) // When the user clicks on the notification, it will be canceled.
                .setPriority(NotificationCompat.PRIORITY_HIGH)
        }

        notificationManager.notify(1, builder.build())
    }
}