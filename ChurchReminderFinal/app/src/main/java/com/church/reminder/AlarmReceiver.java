package com.church.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Receives the weekly scheduled alarm and starts the ReminderService
 * which shows a notification and (optionally) triggers a send via API.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, ReminderService.class);
        serviceIntent.setAction(ReminderService.ACTION_SEND_REMINDERS);
        context.startForegroundService(serviceIntent);
    }
}
