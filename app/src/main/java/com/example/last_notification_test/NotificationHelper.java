package com.example.last_notification_test;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channeNm = "channelNm";

    private NotificationManager notiManager;

    public NotificationHelper(Context base) {
        super(base);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels() {
        NotificationChannel channel1 = new NotificationChannel(channelID, channeNm, NotificationManager.IMPORTANCE_DEFAULT);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLightColor(com.google.android.material.R.color.design_default_color_on_primary);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel1);
    }

    public NotificationManager getManager() {
        if (notiManager == null) {
            notiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return notiManager;
    }

    public NotificationCompat.Builder getChannelNotification() {
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("alarm")
                .setContentText("alarm manager 실행중")
                .setSmallIcon(R.drawable.ic_launcher_background);
    }
}
