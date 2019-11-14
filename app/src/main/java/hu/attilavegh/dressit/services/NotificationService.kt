package hu.attilavegh.dressit.services

import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import hu.attilavegh.dressit.activities.MainActivity
import hu.attilavegh.dressit.R

const val NOTIFICATION_TOPIC = "new_dress"

class NotificationService: FirebaseMessagingService() {

    private lateinit var authentication: FirebaseAuth

    override fun onCreate() {
        super.onCreate()
        authentication = FirebaseAuth.getInstance()

        FirebaseMessaging.getInstance().subscribeToTopic(NOTIFICATION_TOPIC)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val user = authentication.currentUser

        if (user != null) {
            createNotification(remoteMessage)
        }
    }

    private fun createNotification(notification: RemoteMessage) {
        val intent = createIntent()

        val notificationBuilder = NotificationCompat.Builder(this, getString(R.string.notification_new_dress_channelId))
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
            .setSmallIcon(R.drawable.ic_notifications_black_24dp)
            .setContentTitle(getString(R.string.notification_new_dress))
            .setContentIntent(intent)
            .setAutoCancel(true)

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notification.data["id"].hashCode(), notificationBuilder.build())
    }

    private fun createIntent(): PendingIntent {
        val pendingIntentFlags = PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_UPDATE_CURRENT
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        return PendingIntent.getActivity(this, 0, intent, pendingIntentFlags)
    }
}