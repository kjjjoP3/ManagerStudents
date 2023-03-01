package edu.poly.managerstudents.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.List;

import edu.poly.managerstudents.example.models.Students;

public class DatabaseStudent extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="ManagerStuden";
    public static final String TABLE_NAME="Studen";
    public static final String ID="id";
    public static final String TEN="hoten";
    public static final String SDT="sdt";
    public static final String EMAIL="email";
    public static final String IDLOP ="nganh";
    public static final int VERSION=1;
    private Context context;

    public DatabaseStudent(@Nullable Context context) {
        super(context,DATABASE_NAME, null,VERSION);
        this.context=context;
    }
    String Query="CREATE TABLE "+TABLE_NAME+"("+ID +" integer primary key, "+ IDLOP +" TEXT,"+TEN +" TEXT, "+SDT +" TEXT, "+EMAIL +" TEXT)";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addsinhvien(Students students){
        SQLiteDatabase database= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(IDLOP, students.getNganh());
        values.put(TEN, students.getName());
        values.put(SDT, students.getSdt());
        values.put(EMAIL, students.getEmail());
        database.insert(TABLE_NAME,null,values);
        database.close();
    }
    public List<Students> allSinhvien(){
        List<Students> studentsList = new ArrayList<>();
        String Query="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor= database.rawQuery(Query,null);
        if(cursor.moveToFirst()){
            do {
                Students students = new Students();
                students.setId(cursor.getInt(0));
                students.setNganh(cursor.getString(1));
                students.setName(cursor.getString(2));
                students.setSdt(cursor.getString(3));
                students.setEmail(cursor.getString(4));
                studentsList.add(students);
            }while(cursor.moveToNext());
        }
        database.close();

        return studentsList;
    }
    public int Update(Students students){
        SQLiteDatabase database= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(IDLOP, students.getNganh());
        values.put(TEN, students.getName());
        values.put(SDT, students.getSdt());
        values.put(EMAIL, students.getEmail());

        return database.update(TABLE_NAME,values,ID+"=?",new String[]{String.valueOf(students.getId())});
    }
    public int Delete(int id){
        SQLiteDatabase database =this.getWritableDatabase();
        return database.delete(TABLE_NAME,ID+"=?",new String[]{String.valueOf(id)});
    }
}
