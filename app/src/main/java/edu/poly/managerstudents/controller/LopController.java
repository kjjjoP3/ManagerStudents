package edu.poly.managerstudents.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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

    //doc du lieu
    public List<String> getAllLopString(){
        List<String> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null,null,null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            Lop lop = new Lop();
            lop.setMaLop(cursor.getString(0));
            lop.setTenLop(cursor.getString(1));
            list.add(lop.getMaLop() + " - "+lop.getTenLop());
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public int deleteLop(String maLop){
        if(db.delete(TABLE_NAME,"maLop=?", new String[]{maLop})<0){
            return -1;
        }
        else {
            return 1;
        }
    }
}
