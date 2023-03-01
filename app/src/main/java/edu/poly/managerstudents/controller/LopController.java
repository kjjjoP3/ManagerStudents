package edu.poly.managerstudents.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.poly.managerstudents.database.SQLiteDB;
import edu.poly.managerstudents.model.Lop;

public class LopController {

    public static final String SQL_TABLE_LOP = "Create table Lop ( maLop text primary key, tenLop text)";
    public static final String TABLE_NAME = "Lop";
    public SQLiteDB dbHelper;
    public SQLiteDatabase db;
    private Context context;
    public LopController (Context context){
        this.context = context;
        dbHelper = new SQLiteDB(context);
        db = dbHelper.getWritableDatabase();

    }

    //insert
    public int insertLop(Lop lop){
        ContentValues values = new ContentValues();
        values.put("maLop", lop.getMaLop());
        values.put("tenLop", lop.getTenLop());
        if(db.insert(TABLE_NAME, null, values)<0){
            return -1; //insert khong thanh cong
        }
        return 2; // insert thanh cong
    }
}
