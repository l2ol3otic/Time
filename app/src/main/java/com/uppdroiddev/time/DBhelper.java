package com.uppdroiddev.time;

/**
 * Created by l2ol3otic2 on 2/4/2560.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.uppdroiddev.time.Model.place;


public class DBhelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();
    private SQLiteDatabase sqLiteDatabase;

    public DBhelper(Context context) {
        super(context, place.DATABASE_NAME, null, place.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_PLACE_TABLE = String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY  AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                place.TABLE,
                place.Column.ID,
                place.Column.timeH,
                place.Column.timeM,
                place.Column.floor,
                place.Column.place);


        Log.i(TAG, CREATE_PLACE_TABLE);

        // create place table
        sqLiteDatabase.execSQL(CREATE_PLACE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String DROP_PLACE_TABLE = "DROP TABLE IF EXISTS " + place.TABLE;

        sqLiteDatabase.execSQL(DROP_PLACE_TABLE);

        Log.i(TAG, "Upgrade Database from " + i + " to " + i1);

        onCreate(sqLiteDatabase);
    }

    public void addPlace(place place2){

        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(Friend.Column.ID, friend.getId());
        values.put(place.Column.timeH, place2.getTimeH());
        values.put(place.Column.timeM, place2.getTimeM());
        values.put(place.Column.floor, place2.getFloor());
        values.put(place.Column.place, place2.getPlace());


        sqLiteDatabase.insert(place.TABLE, null, values);

        sqLiteDatabase.close();
    }
}
