package edu.poly.managerstudents.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



import java.util.ArrayList;
import java.util.List;

import edu.poly.managerstudents.example.models.Classroom;

public class DatabaseClass extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Manager";
    public static final String TABLE_NAME="ClassManeger";
    public static final String ID="id";
    public static final String MALOP="malop";
    public static final String TENLOP="tenlop";
    public static final int VERSION =1;
    private Context context;


    String Query="CREATE TABLE "+TABLE_NAME+"("+ID +" integer primary key, "+MALOP +" TEXT, "+TENLOP +" TEXT)";


    public DatabaseClass(@Nullable Context context) {
        super(context,DATABASE_NAME, null, VERSION);
        this.context=context;


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

     db.execSQL(Query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addlop(Classroom lop){
        SQLiteDatabase database= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(MALOP,lop.getIdlop());
        values.put(TENLOP,lop.getTenlop());
        database.insert(TABLE_NAME,null,values);
        database.close();
    }
    public List<Classroom> Alllop(){
        List<Classroom> list = new ArrayList<>();
        String selectQuery="SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase database =this.getWritableDatabase();
       Cursor cursor= database.rawQuery(selectQuery,null);
       if(cursor.moveToFirst()){
           do {
            Classroom lop = new Classroom();
            lop.setId(cursor.getInt(0));
            lop.setIdlop(cursor.getString(1));
            lop.setTenlop(cursor.getString(2));
            list.add(lop);
           }while (cursor.moveToNext());
       }
       database.close();
       return list;
    }
    public int Update(Classroom lop){
        SQLiteDatabase database= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(MALOP,lop.getIdlop());
        values.put(TENLOP,lop.getTenlop());
        return database.update(TABLE_NAME,values,ID+"=?",new String[]{String.valueOf(lop.getId())});
    }
    public int Delete(int id){
        SQLiteDatabase database =this.getWritableDatabase();
        return database.delete(TABLE_NAME,ID+"=?",new String[]{String.valueOf(id)});
    }

}
