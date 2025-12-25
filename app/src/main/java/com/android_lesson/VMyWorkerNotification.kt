package com.android_lesson

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class VMyWorkerNotification(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        createNotification()
        return Result.success()
    }

    private fun createNotification() {

        val builder: NotificationCompat.Builder
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(applicationContext, VActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(applicationContext, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        // version check
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channelId = "channelId2"
            val channelName = "Channel Name2" // appears in the notification settings
            val channelDescription = "Channel Description2"
            val channelImportance = NotificationManager.IMPORTANCE_HIGH

            val channel: NotificationChannel? =
                notificationManager.getNotificationChannel(channelId)

            if (channel == null) {
                notificationManager.createNotificationChannel(
                    NotificationChannel(
                        channelId,
                        channelName,
                        channelImportance
                    ).apply {
                        description = channelDescription
                    }
                )
            }

            builder = NotificationCompat.Builder(applicationContext, channelId)
            builder.setContentTitle("Title")
                .setContentText("Content")
                .setSmallIcon(R.drawable.baseline_cruelty_free_24)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true) // When the user clicks on the notification, it will be canceled/disappeared.
        } else {
            builder = NotificationCompat.Builder(applicationContext)
            builder.setContentTitle("Title")
                .setContentText("Content")
                .setSmallIcon(R.drawable.baseline_cruelty_free_24)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true) // When the user clicks on the notification, it will be canceled/disappeared.
                .priority = NotificationCompat.PRIORITY_HIGH
        }

        notificationManager.notify(1, builder.build())
    }
}