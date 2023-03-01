package edu.poly.managerstudents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.poly.managerstudents.controller.LopController;
import edu.poly.managerstudents.model.Lop;

public class ThemLopActivity extends AppCompatActivity {

    EditText txtMaLop, txtTenLop;
    Button btnThem;
    LopController lopController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_lop);
        txtMaLop = findViewById(R.id.edMaLop);
        txtTenLop = findViewById(R.id.edTenLop);
        lopController = new LopController(this);
    }

    public void addNewLop(View view){
        Lop lop = new Lop();
        lop.setMaLop(txtMaLop.getText().toString());
        lop.setTenLop(txtTenLop.getText().toString());
        if(lopController.insertLop(lop) < 0){
            Toast.makeText(ThemLopActivity.this,"them khong thanh cong", Toast.LENGTH_LONG).show();
            System.out.println("khong thanh cong");
        }
        else {
            Toast.makeText(ThemLopActivity.this,"them thanh cong", Toast.LENGTH_LONG).show();
            System.out.println("thanh cong");
        }
    }
}