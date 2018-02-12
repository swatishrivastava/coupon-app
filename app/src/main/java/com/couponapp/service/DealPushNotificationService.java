package com.couponapp.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.couponapp.BaseCouponApplication;
import com.couponapp.home.DealsHomeActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import example.couponapp.com.couponapp.R;


public class DealPushNotificationService extends FirebaseMessagingService {

    private static final String TAG = DealPushNotificationService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "***********Message Notification Body: ************" +
                    remoteMessage.getNotification()
                            .getBody());

            sendNotification(remoteMessage.getNotification()
                                     .getTitle(),remoteMessage.getNotification()
                                     .getBody());
        }



    }

    private void sendNotification(String notificationTitle, String notificationBody) {
        Intent intent = new Intent(this, DealsHomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                                                                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setAutoCancel(true)   //Automatically delete the notification
                .setSmallIcon(R.mipmap.ic_launcher) //Notification icon
                .setContentIntent(pendingIntent)
                .setContentTitle(notificationTitle)
                .setContentText(notificationBody)
                .setSound(defaultSoundUri);


        NotificationManager notificationManager = (NotificationManager) getSystemService(
                BaseCouponApplication.getAppContext().NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}
