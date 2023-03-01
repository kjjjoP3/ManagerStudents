package edu.poly.managerstudents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThemLopActivity extends AppCompatActivity {

    EditText txtMaLop, txtTenLop;
    Button btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_lop);
        txtMaLop = findViewById(R.id.edMaLop);
        txtTenLop = findViewById(R.id.edTenLop);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}