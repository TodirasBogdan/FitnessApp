package com.example.fitnessapp;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DBHelper extends SQLiteOpenHelper {

    //public static String DB_PATH = "/data/data/" + BuildConfig.APPLICATION_ID + "/databases/";
    public static final String DB_NAME = "FitFuelDB.db";
    public static final String userTable = "users";
    public static final String foodTable = "Food";
    public static final String workoutTable = "Workout";
    //public Context context;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        //this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase mySqLiteDatabase) {
        System.out.println("HELLO");
            mySqLiteDatabase.execSQL("create Table " + userTable + " (username TEXT primary key, password TEXT, age TEXT, height TEXT, weight TEXT)");
            mySqLiteDatabase.execSQL("create Table " + foodTable + " (idFood TEXT  primary key AUTOINCREMENT, name TEXT, calorieDensity TEXT)");
            mySqLiteDatabase.execSQL("create Table " + workoutTable + " (idWorkout TEXT  primary key AUTOINCREMENT,type TEXT, caloriesPerMinute TEXT)");
            //mySqLiteDatabase.execSQL("create Table "+"FoodUser"+"(idFood TEXT, username TEXT, quantityInGrams TEXT, primary key(idFood,username),foreign key(idFood) references Food(idFood) ,foreign key(username) references User(username))");
            //mySqLiteDatabase.execSQL("create Table "+"WorkoutUser"+"(idWorkout TEXT, username TEXT, durationInMinutes TEXT, primary key(idWorkout,username),foreign key(idWorkout) references Workout(idWorkout) ,foreign key(username) references User(username))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase mySqLiteDatabase, int i, int i1) {
        System.out.println("HELLOUPGRADE");
        //mySqLiteDatabase.execSQL("drop Table if exists FoodUser");
        //mySqLiteDatabase.execSQL("drop Table if exists WorkoutUser");
        mySqLiteDatabase.execSQL("drop Table if exists " + userTable);
        mySqLiteDatabase.execSQL("drop Table if exists " + foodTable);
        mySqLiteDatabase.execSQL("drop Table if exists " + workoutTable);
        onCreate(mySqLiteDatabase);
    }

    public Boolean insertDataUser(String username, String password, String age, String height, String weight) {
        SQLiteDatabase mySqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("age", age);
        contentValues.put("height", height);
        contentValues.put("weight", weight);
        long result = mySqLiteDatabase.insert(userTable, null, contentValues);
        System.out.println("HELLOINSERT " + result);
        return result != -1;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase mySqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = mySqLiteDatabase.rawQuery("Select * from " + userTable + " where username = ?", new String[]{username});
        return cursor.getCount() > 0;
    }

    public Boolean checkUsernameAndPassword(String username, String password) {
        SQLiteDatabase mySqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = mySqLiteDatabase.rawQuery("Select * from " + userTable + " where username = ? and password = ?", new String[]{username, password});
        return cursor.getCount() > 0;
    }


}
