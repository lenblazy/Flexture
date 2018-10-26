package com.example.lennox.flexture;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "schedule.db";
    public static final String TABLE_SCHEDULE = "schedule";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_VENUE = "venue";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + TABLE_SCHEDULE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CODE + " TEXT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_VENUE + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_TIME + " TEXT )";

        sqLiteDatabase.execSQL(query);

        //adding dummy data
        try {
            ////////////////////////////////////////////////////////////////////////
            sqLiteDatabase.execSQL("insert into schedule (code,title,venue,date,time )"
                    + "values ('CSC 123','Introduction to Programming','LBB 011','2018-10-24','15:00')");
            sqLiteDatabase.execSQL("insert into schedule (code,title,venue,date,time )"
                    + "values ('CSC 213','Data Structures and Algorithms','SPH 004','2018-10-23','8:00')");
            sqLiteDatabase.execSQL("insert into schedule (code,title,venue,date,time )"
                    + "values ('CSC 415','Project management','LBB 016','2018-10-24','13:00')");
            sqLiteDatabase.execSQL("insert into schedule (code,title,venue,date,time )"
                    + "values ('CSC 323','User Interface design','MED 004','2018-11-24','15:00')");
            sqLiteDatabase.execSQL("insert into schedule (code,title,venue,date,time )"
                    + "values ('CSC 123','Introduction to Programming','LBB 011','2018-10-24','15:00')");
            sqLiteDatabase.execSQL("insert into schedule (code,title,venue,date,time )"
                    + "values ('CSC 123','Introduction to Programming','LBB 011','2018-10-24','15:00')");

            Log.v("DataBase", "DB created and values added : ");

        } catch (Exception e) {
            Log.d("CS", "Error in DBHelper.onCreate() : " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULE);
        onCreate(sqLiteDatabase);
    }

    //add new row to the db
    public boolean addSchedule(Classes classes) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_CODE, classes.getCode());
            values.put(COLUMN_TITLE, classes.getTitle());
            values.put(COLUMN_VENUE, classes.getVenue());
            values.put(COLUMN_DATE, classes.getDate());
            values.put(COLUMN_TIME, classes.getTime());

            long result = db.insert(TABLE_SCHEDULE, null, values);
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
        return false;
    }//implement to add values in code

    //delete row from db
    public void deleteSchedule(String code) {
        SQLiteDatabase db = getWritableDatabase();
        //db.delete(TABLE_SCHEDULE + COLUMN_CODE + "=" + code, null);
        db.close();
    }

    public static ArrayList<Classes> getClasses(Context scheduler) {
        DBHandler dbHandler = new DBHandler(scheduler);
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        ArrayList<Classes> list = new ArrayList<>();

        String[] columns = {COLUMN_CODE, COLUMN_TITLE, COLUMN_VENUE, COLUMN_DATE, COLUMN_TIME};

        Cursor c = db.query(TABLE_SCHEDULE, columns, null, null, null, null, null, null);

        while (c.moveToNext()) {
            Classes classes = DBHandler.cursorToClasses(c);
            list.add(classes);
        }
        c.close();
        db.close();
        dbHandler.close();

        return list;
    }

    private static Classes cursorToClasses(Cursor c) {
        Classes cls = new Classes();
        cls.setID(c.getInt(c.getColumnIndex(COLUMN_ID)));
        cls.setCode(c.getString(c.getColumnIndex(COLUMN_CODE)));
        cls.setTime(c.getString(c.getColumnIndex(COLUMN_TITLE)));
        cls.setVenue(c.getString(c.getColumnIndex(COLUMN_VENUE)));
        cls.setDate(c.getString(c.getColumnIndex(COLUMN_DATE)));
        cls.setTime(c.getString(c.getColumnIndex(COLUMN_TIME)));
        return cls;
    }

}
