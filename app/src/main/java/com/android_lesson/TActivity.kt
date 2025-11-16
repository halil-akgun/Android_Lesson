package com.android_lesson

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android_lesson.databinding.ActivityTBinding

class TActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityTBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityTBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewBinding.button36.setOnClickListener {
            val builder: NotificationCompat.Builder

            val notificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            val intent = Intent(this, TActivity::class.java)
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
                builder.setContentTitle("Title")
                    .setContentText("Content")
                    .setSmallIcon(R.drawable.baseline_cruelty_free_24)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true) // When the user clicks on the notification, it will be canceled/disappeared.
            } else {
                builder = NotificationCompat.Builder(this)
                builder.setContentTitle("Title")
                    .setContentText("Content")
                    .setSmallIcon(R.drawable.baseline_cruelty_free_24)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true) // When the user clicks on the notification, it will be canceled/disappeared.
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
            }

            notificationManager.notify(1, builder.build())
        }
    }
}