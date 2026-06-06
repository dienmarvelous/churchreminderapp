package com.church.reminder;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

/**
 * Foreground service that fires when the weekly alarm triggers.
 * Shows a notification reminding the church worker to open the app
 * and confirms that scheduled API sends are running.
 */
public class ReminderService extends Service {

    public static final String ACTION_SEND_REMINDERS = "com.church.reminder.SEND_REMINDERS";
    private static final String CHANNEL_ID   = "church_reminder_channel";
    private static final int    NOTIFICATION_ID = 1001;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotificationChannel();
        startForeground(NOTIFICATION_ID, buildNotification());

        // The actual API calls (SMS + Voice) are made from the WebView (JavaScript).
        // This service exists to:
        //   1. Keep the app alive in the background during sends
        //   2. Show a persistent notification so the user knows reminders are going out
        // The WebView JS already handles the full send loop with progress tracking.

        stopSelf(); // done after showing notification
        return START_NOT_STICKY;
    }

    private Notification buildNotification() {
        Intent openApp = new Intent(this, MainActivity.class);
        openApp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pi = PendingIntent.getActivity(this, 0, openApp,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Church Reminder — Sending Now")
                .setContentText("Sunday Service reminders are being sent to all members.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Church Reminders",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Weekly Sunday Service reminder notifications");
            NotificationManager mgr = getSystemService(NotificationManager.class);
            if (mgr != null) mgr.createNotificationChannel(channel);
        }
    }

    @Override
    public IBinder onBind(Intent intent) { return null; }
}
