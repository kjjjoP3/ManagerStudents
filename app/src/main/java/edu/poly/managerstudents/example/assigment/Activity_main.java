package com.example.assigment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_main extends AppCompatActivity {
    private Button qll, alll, qlsv, allsv;
    private String struser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qll = findViewById(R.id.btn_mngclass);
        alll = findViewById(R.id.btn_alll);
        qlsv = findViewById(R.id.btn_mngstd);
        allsv = findViewById(R.id.btn_allstd);

    }

    public void managerClass(View view) {
        Intent intent = new Intent(this, Activity_ManagerClass.class);
        startActivity(intent);
    }

    public void allClass(View view) {
        Intent intent = new Intent(this, Activity_AllClass.class);
        startActivity(intent);
    }

    public void managerStudent(View view) {
        Intent intent = new Intent(this, Activity_StudentManager.class);
        startActivity(intent);
    }

    public void allStudent(View view) {
        Intent intent = new Intent(this, Activity_AllStudent.class);
        startActivity(intent);
    }

}
