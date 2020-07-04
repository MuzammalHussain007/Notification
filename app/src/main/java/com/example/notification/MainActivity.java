package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String CHENNAL_ID = "Hello";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void EnableNotification(View view) {

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.abc);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHENNAL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.GetString)))
                .setLargeIcon(bitmap)
                .setStyle(new NotificationCompat.BigPictureStyle().bigLargeIcon(null))

                .setContentText("Hello Iam Notification")
                .setContentTitle("Notification")
                .setAutoCancel(true)
                .setLights(0xff00ff00, 10, 1000)   // Enable Light
                .setDefaults(NotificationCompat.DEFAULT_SOUND) //Pop up Notification
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH);// Pop up Notification


        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
        int notifiID=getNotificationid();
        managerCompat.notify(notifiID, builder.build());
        createNotificationChannel();


    }
    private int getNotificationid()
    {
        int [] id = new int[] {1,2,3,4};
        return id[(int) (Math.random()*id.length)];
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "abc";
            String description = "Hello I am Dr mashore qulati";
            int importance = NotificationManager.IMPORTANCE_HIGH; // Pop Up notification
            NotificationChannel channel = new NotificationChannel(CHENNAL_ID, name, importance);
            channel.enableLights(true);
            channel.setLightColor(Color.GREEN);
            channel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}