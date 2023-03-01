package com.example.assigment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.database.DatabaseStudent;
import com.example.models.Students;

import java.util.ArrayList;
import java.util.List;

import Adapter.CustomAdapterStudent;

public class Activity_AllStudent extends AppCompatActivity {
    private ListView listView;
    private CustomAdapterStudent customAdapterStudent;
    private List<Students> studentsList;
    private DatabaseStudent databaseStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__all_student);
        listView = findViewById(R.id.lv_allsv);
        databaseStudent = new DatabaseStudent(this);
        studentsList = databaseStudent.allSinhvien();
        Setadapter();
        checkrequet();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogConfirm(position);
            }
        });

    }

    private void Setadapter() {
        if (customAdapterStudent == null) {
            customAdapterStudent = new CustomAdapterStudent(this, R.layout.item_sinhvien, studentsList);
        }
        listView.setAdapter(customAdapterStudent);
    }

    public void showDialogConfirm(final int position) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.call_sms);
        Button btncall = (Button) dialog.findViewById(R.id.btn_call);
        Button btnsms = (Button) dialog.findViewById(R.id.btn_send);
        dialog.show();

        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intenCall(position);

            }
        });
        btnsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intenSms(position);
            }
        });

    }

    private void intenCall(int position) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + studentsList.get(position).getSdt()));

        startActivity(intent);

    }

    private void intenSms(int position) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:" + studentsList.get(position).getSdt()));
        startActivity(intent);
    }

    private void checkrequet() {
        String[] permision = new String[]{
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS
        };
        List<String> lisPermisionNeed = new ArrayList<>();
        for (String permisions : permision) {
            if (ContextCompat.checkSelfPermission(this, permisions) != PackageManager.PERMISSION_GRANTED) {
                lisPermisionNeed.add(permisions);
            }
        }
        if (!lisPermisionNeed.isEmpty()) {
            ActivityCompat.requestPermissions(this, lisPermisionNeed.toArray(new String[lisPermisionNeed.size()]), 1);
        }
    }

}
