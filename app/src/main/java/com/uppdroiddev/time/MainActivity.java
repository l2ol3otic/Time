package com.uppdroiddev.time;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.uppdroiddev.time.Model.place;
import com.uppdroiddev.time.DBhelper;
import com.uppdroiddev.time.Service.Tservice;

public class MainActivity extends AppCompatActivity {

    TimePicker tp;
    TextView tv;
    private DBhelper mHelper;

    private int ID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt1 = (Button)findViewById(R.id.bt);
        mHelper = new DBhelper(this);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tp =(TimePicker) findViewById(R.id.tp1);
                int a = tp.getHour();
                int b = tp.getMinute();
                int c = 40;
                int d = 120;
                place place2 = new place();
                place2.setTimeH(String.valueOf(a));
                place2.setTimeM(String.valueOf(b));
                place2.setTimeH(String.valueOf(c));
                place2.setTimeH(String.valueOf(d));

                if (ID == -1) {
                    mHelper.addPlace(place2);
                } else {
                    place2.setId(ID);
                    //mHelper.updateFriend(friend);
                }
                //finish();
                Log.e("a ",String.valueOf(a));
                Log.e("b ",String.valueOf(b));
                showtime(a,b);

                /*Calendar c2 = Calendar.getInstance();
                int seconds = c2.get(Calendar.MINUTE);
                Log.i("Time",String.valueOf(seconds));*/

                startService(new Intent(MainActivity.this, Tservice.class));
                startActivity(new Intent(MainActivity.this, progressActivity.class));
            }
        });


    }

    private void showtime(int a, int b) {

        tv =(TextView)findViewById(R.id.tv1);
        tv.setText(String.valueOf(a)+ " : " +String.valueOf(b));
    }



}
