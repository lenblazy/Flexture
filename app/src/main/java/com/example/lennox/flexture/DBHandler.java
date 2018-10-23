package com.example.lennox.flexture;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "schedule.db";
    public static final String TABLE_SCHEDULE = "schedule";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_VENUE = "venue";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + TABLE_SCHEDULE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CODE + " TEXT, " +
         COLUMN_TITLE + " TEXT, " +
         COLUMN_VENUE + " TEXT, " +
       COLUMN_DATE + " TEXT, " +
         COLUMN_TIME + " TEXT " +
                ");";


         sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULE);
        onCreate(sqLiteDatabase);
    }

    //add new row to the db
    public void addSchedule(Classes classes){
        ContentValues values = new ContentValues();
        values.put(COLUMN_CODE, classes.getCode());
        values.put(COLUMN_TITLE, classes.getTitle());
        values.put(COLUMN_VENUE, classes.getVenue());
        values.put(COLUMN_DATE, classes.getDate());
        values.put(COLUMN_TIME, classes.getTime());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_SCHEDULE, null, values);
        db.close();
    }

    //delete row from db
    public void deleteSchedule(String code){
        SQLiteDatabase db = getWritableDatabase();
       // db.delete(TABLE_SCHEDULE + COLUMN_CODE + "=" + code, null);
        db.close();
    }

    public static ArrayList<Classes> getClasses(Scheduler scheduler) {
        DBHandler dbHandler = new DBHandler(scheduler, null, null, 1);
        String dbString = dbHandler.dbToString();

        ArrayList<Classes> list = new ArrayList<>();


        return list;
    }// this is wrong

    public String dbToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SCHEDULE + "";

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //move to the first row in your results
        c.moveToFirst();

        while (!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("code"))!= null){
                dbString += c.getString(c.getColumnIndex("code"));
                dbString += c.getString(c.getColumnIndex("title"));
                dbString += c.getString(c.getColumnIndex("venue"));
                dbString += c.getString(c.getColumnIndex("date"));
                dbString += c.getString(c.getColumnIndex("time"));
                dbString += "\n";

            }
        }


        return dbString;
    }

}
