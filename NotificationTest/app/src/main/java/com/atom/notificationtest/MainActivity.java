package com.atom.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendNotice = findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_notice:
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                NotificationManager manager = (NotificationManager) getSystemService
                        (NOTIFICATION_SERVICE);
                Notification notification = new NotificationCompat.Builder(MainActivity.this, "default")
                        .setContentTitle("---- title ----")
                        .setContentText("==== text ====")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                                R.mipmap.ic_launcher))
                        .setContentIntent(pi)
                        // .setAutoCancel(true) // 第一种通知栏自动消失的方法
                        .build();
                manager.notify(1, notification);
                break;
            default:
                break;


        }
    }
}
