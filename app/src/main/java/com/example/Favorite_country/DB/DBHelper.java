package com.example.Favorite_country.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static DBHelper sqLiteHelper = null;
    public static final String DATABASE_NAME = "favorite_country.db";
    public static final String TABLE_NAME = "favorite_country";
    public static final int DB_VERSION = 1;
    public static final String IDX = "IDX";
    public static final String COL_0 = "country";
    public static final String COL_1 = "country_eng";
    public static final String COL_2 = "flag";
    public static final String COL_3 = "capital";

    private SQLiteDatabase db;

    public static DBHelper getInstance(Context context) {
        if(sqLiteHelper == null){
            sqLiteHelper = new DBHelper(context);
        }
        return sqLiteHelper;
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
        db = this.getWritableDatabase();
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TABLE_NAME + " ("
                + IDX + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_0 + " TEXT, "
                + COL_1 + " TEXT, "
                + COL_2 + " INT, "
                + COL_3 + " TEXT "
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
