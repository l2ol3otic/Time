package com.uppdroiddev.time.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.uppdroiddev.time.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by l2ol3otic2 on 2/4/2560.
 */

public class TserviceBackup extends Service {

    Timer timer;
    public String start;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;

    }
    @Override
    public void onCreate() {
        Toast.makeText(getApplication(), "Start Service!", Toast.LENGTH_LONG).show();
        Calendar calendar1 = Calendar.getInstance();
        SimpleDateFormat formatter1 = new SimpleDateFormat("h:mm:ss");
        start = formatter1.format(calendar1.getTime());
        Log.i("Time",start);
        shownotification();
        CountDownTimer cdt = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Tick
            }

            public void onFinish() {

                // Finish
            }
        }.start();
        //shownotification();
        Log.i("Service : ","Service Start");


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplication(), "Stop Service!", Toast.LENGTH_LONG).show();
        Log.i("Service : ","Service Destroy");
        Calendar calendar1 = Calendar.getInstance();
        SimpleDateFormat formatter1 = new SimpleDateFormat("h:mm:ss");
        String stop = formatter1.format(calendar1.getTime());
        Log.i("Time",stop);
        Difftime(start,stop);

    }

    private void Difftime(String Tstart, String Tstop) {

        DateFormat df = new SimpleDateFormat("h:mm:ss");
        try {
            Date start = df.parse(Tstart);
            Date end = df.parse(Tstop);

            long diff = end.getTime() - start.getTime();

            //int dayDiff = (int) (diff / (24 * 60 * 60 * 1000));
            int hourDiff = (int) (diff / (60 * 60 * 1000) % 24);
            int minuteDiff = (int) (diff / (60 * 1000) % 60);
            int secondDiff = (int) (diff / 1000 % 60);

            //System.out.println("Day Diff = " + dayDiff);
            System.out.println("Hour Diff = " + hourDiff);
            System.out.println("Minute Diff = " + minuteDiff);
            System.out.println("Second Diff = " + secondDiff);



        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void shownotification() {
        Notification notification =
                new NotificationCompat.Builder(this) // this is context
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("CarParking")
                        .setContentText("Start Service")
                        .setAutoCancel(true)
                        .build();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1000, notification);
        //timer = new Timer();
        //timer.schedule(new MyTask(), 1000, 0);
    }

    private class MyTask extends TimerTask {

        @Override
        public void run() {
            Log.i("Service : ","Service Destroy");
        }
    }

}
