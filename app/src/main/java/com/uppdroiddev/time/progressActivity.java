package com.uppdroiddev.time;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.uppdroiddev.time.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static android.support.v7.appcompat.R.styleable.View;

/**
 * Created by it02 on 3/4/2560.
 */

public class progressActivity extends Activity {
    int count=0;
    String time;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress);
        Button bt1 = (Button) findViewById(R.id.bt1);
        final TextView etx = (TextView) findViewById(R.id.timetx);
        Timer T=new Timer();
        Calendar calendar2 = Calendar.getInstance();
        SimpleDateFormat formatter2 = new SimpleDateFormat("h:mm:ss");
        final String time2 = formatter2.format(calendar2.getTime());

        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Calendar calendar1 = Calendar.getInstance();
                        SimpleDateFormat formatter1 = new SimpleDateFormat("h:mm:ss");
                        time = formatter1.format(calendar1.getTime());
                        //DateFormat df = new SimpleDateFormat("h:mm:ss");
                        try {
                            DateFormat df = new SimpleDateFormat("h:mm:ss");
                            Date start = df.parse(time2);
                            Date end = df.parse(time);

                            long diff = end.getTime() - start.getTime();

                            //int dayDiff = (int) (diff / (24 * 60 * 60 * 1000));
                            int hourDiff = (int) (diff / (60 * 60 * 1000) % 24);
                            int minuteDiff = (int) (diff / (60 * 1000) % 60);
                            int secondDiff = (int) (diff / 1000 % 60);

                            //System.out.println("Day Diff = " + dayDiff);
                            System.out.println("Time2 = " + time2);
                            System.out.println("Time1 = " + time);
                            System.out.println("Hour Diff = " + hourDiff);
                            System.out.println("Minute Diff = " + minuteDiff);
                            System.out.println("Second Diff = " + secondDiff);
                            etx.setText(hourDiff+":"+minuteDiff+":"+secondDiff);



                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        System.out.println(count);
                        count++;
                    }
                });
            }
        }, 1000, 1000);

    }


}
