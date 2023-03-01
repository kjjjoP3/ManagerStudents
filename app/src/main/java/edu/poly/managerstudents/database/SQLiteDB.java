package edu.poly.managerstudents.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import edu.poly.managerstudents.controller.LopController;

public class SQLiteDB extends SQLiteOpenHelper {

    private Context context;
    public static final String DB_NAME = "qlsv";
    public static  final int VERSION = 1;

    //khoi tao db
    public SQLiteDB(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }
    //tao bang
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LopController.SQL_TABLE_LOP);
    }
    //upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+LopController.TABLE_NAME);
    }
}
