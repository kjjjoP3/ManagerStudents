package edu.poly.managerstudents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnAdd, btnList, btnQLSV;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAddLop);
        btnList = findViewById(R.id.btnListLop);
        btnQLSV = findViewById(R.id.btnQLSV);


    }

    public void themLop(View view){
        intent =  new Intent(MainActivity.this, ThemLopActivity.class);
        startActivity(intent);
    }

    public void ListLop(View view){
        intent =  new Intent(MainActivity.this, ListLopActivity.class);
        startActivity(intent);
    }

    public void qlsv(){
        intent =  new Intent(MainActivity.this, QLSVActivity.class);
        startActivity(intent);
    }
}