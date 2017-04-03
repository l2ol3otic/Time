package com.uppdroiddev.time.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
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

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by l2ol3otic2 on 2/4/2560.
 */

public class Tservice extends Service {


    Timer timer;
    public String time8,time2;
    public String start,start2;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;

    }
    @Override
    public void onCreate() {
        Toast.makeText(getApplication(), "Start Service!", Toast.LENGTH_LONG).show();
        Log.i("Service : ", "Service Start");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm:ss");
        start2 = formatter.format(calendar.getTime());
        Log.i("Time", start2);
        Log.i("Service : ", "Service Start");
        final Timer T = new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                                Calendar calendar1 = Calendar.getInstance();
                                SimpleDateFormat formatter1 = new SimpleDateFormat("h:mm:ss");
                                time8 = formatter1.format(calendar1.getTime());
                                //DateFormat df = new SimpleDateFormat("h:mm:ss");
                                try {
                                    DateFormat df = new SimpleDateFormat("h:mm:ss");
                                    Date start = df.parse(start2);
                                    Date end = df.parse(time8);

                                    long diff = end.getTime() - start.getTime();

                                    //int dayDiff = (int) (diff / (24 * 60 * 60 * 1000));
                                    int hourDiff = (int) (diff / (60 * 60 * 1000) % 24);
                                    int minuteDiff = (int) (diff / (60 * 1000) % 60);
                                    int secondDiff = (int) (diff / 1000 % 60);

                                    //System.out.println("Day Diff = " + dayDiff);
                                    System.out.println("Time2 = " + start2);
                                    System.out.println("Time1 = " + time8);
                                    System.out.println("Hour Diff = " + hourDiff);
                                    System.out.println("Minute Diff = " + minuteDiff);
                                    System.out.println("Second Diff = " + secondDiff);
                                    //etx.setText(hourDiff+":"+minuteDiff+":"+secondDiff);
                                    //shownotification();
                                    if(secondDiff == 20){
                                        T.cancel();
                                        preDestroy();
                                    }


                                } catch (ParseException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();

                                }
                                //System.out.println(count);
                                //count++;

                            }

        }, 1000, 1000);

    }

    private void preDestroy() {
        onDestroy();
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
        //Difftime(start,stop);

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


    }

}
