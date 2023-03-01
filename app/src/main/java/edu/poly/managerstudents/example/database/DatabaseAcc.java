package edu.poly.managerstudents.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.models.Account;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAcc extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "taikhoandangnhap";
    private static final String TABLE_NAME = "taikhoan";
    private static final String USER = "user";
    private static final String PASS = "pass";
    private static final int VERSION = 1;
    String Query = "CREATE TABLE " + TABLE_NAME + "(" + USER + " TEXT, " + PASS + " TEXT)";

    public DatabaseAcc(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addlop(Account account) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER, account.getUser());
        values.put(PASS, account.getPassword());
        database.insert(TABLE_NAME, null, values);
        database.close();
    }

    public List<String> userList() {

        List<String> accountList = new ArrayList<>();
        String query = "SELECT user from taikhoan";
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do{
                String user;
                user=cursor.getString(0);
                accountList.add(user);
            }while (cursor.moveToNext()) ;
        }
        database.close();
        return accountList;
    }

    public List<String> passList() {

        List<String> accountList = new ArrayList<>();
        String query = "SELECT pass from taikhoan";
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do{
                String user;
                user=cursor.getString(0);
                accountList.add(user);
            }while (cursor.moveToNext()) ;
        }
        database.close();
        return accountList;
    }
}
