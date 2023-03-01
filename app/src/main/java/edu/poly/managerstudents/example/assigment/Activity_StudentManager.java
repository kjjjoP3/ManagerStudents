package com.example.assigment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.database.DatabaseClass;
import com.example.database.DatabaseStudent;
import com.example.models.Classroom;
import com.example.models.Students;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import Adapter.CustomAdapterStudent;

public class Activity_StudentManager extends AppCompatActivity {
    private EditText ten, sdt, email, stt;
    private Button reset, save, update;
    private Spinner lislop;
    private ListView listView;
    private Context context;
    private DatabaseStudent databaseStudent;
    private CustomAdapterStudent customAdapterStudent;
    private List<Students> students;
    private String nganh = null;
    private List<Classroom> lopList = new ArrayList<>();
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String NUMBER_PHONE = "^0[987]{1}\\d{8}$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_l_s_v);
        dumy();
        databaseStudent = new DatabaseStudent(this);
        students = databaseStudent.allSinhvien();
        setadapterIDlop();
        Setadapter();

        lislop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nganh = lopList.get(lislop.getSelectedItemPosition()).getIdlop();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Students students = new Students();
                students.setId(Integer.parseInt(stt.getText().toString()));
                students.setNganh(nganh);
                students.setName(ten.getText().toString());
                students.setSdt(sdt.getText().toString());
                students.setEmail(email.getText().toString());


                try {
                    int result = databaseStudent.Update(students);

                    if (result > 0) {
                        Toast.makeText(getApplicationContext(), "UPDATE THANH CONG", Toast.LENGTH_SHORT).show();
                        Activity_StudentManager.this.students.clear();
                        Activity_StudentManager.this.students.addAll(databaseStudent.allSinhvien());
                        customAdapterStudent.notifyDataSetChanged();
                        save.setEnabled(true);
                        update.setEnabled(false);

                    }

                } catch (Exception e) {
                    Log.e("ERROR", e.toString());
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Students students = Activity_StudentManager.this.students.get(position);
                stt.setText(String.valueOf(students.getId()));
                String maLop = students.getNganh();
                ArrayAdapter<Classroom> adapter = (ArrayAdapter<Classroom>) lislop.getAdapter();
                for (int i = 0; i < adapter.getCount(); i++) {
                    Classroom lop = (Classroom) adapter.getItem(i);
                    if (lop.getIdlop().equals(maLop)) {
                        lislop.setSelection(i);
                    }
                }
                ten.setText(students.getName());
                sdt.setText(students.getSdt());
                email.setText(students.getEmail());

                save.setEnabled(false);
                update.setEnabled(true);

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                showdialog(position);
                return false;
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ht = ten.getText().toString();
                String so = sdt.getText().toString();
                String mail = email.getText().toString();
                String n = nganh;


                try {
                    if (ht.isEmpty()) {
                        ten.setError("TÊN KHÔNG ĐƯỢC TRỐNG");
                        return;
                    }

                    if (so.isEmpty()) {
                        sdt.setError("SDT KHÔNG ĐƯỢC TRỐNG");
                        return;
                    }
                    Pattern pattern = Pattern.compile(NUMBER_PHONE);
                    Matcher matchersdt = pattern.matcher(so);
                    if (matchersdt.find() == false) {
                        sdt.setError("SDT KHÔNG ĐÚNG ĐỊNH DẠNG  ");
                        return;
                    }
                    if (mail.isEmpty()) {
                        email.setError("Email không được trống");
                        return;
                    }
                    Pattern patternN = Pattern.compile(EMAIL_PATTERN);
                    Matcher matcherN = patternN.matcher(mail);
                    if (matcherN.find() == false) {
                        email.setError("Email không đúng định dạng");
                        return;
                    }
                } catch (Exception e) {

                }
                Students students = new Students(ht, n, mail, so);
                databaseStudent.addsinhvien(students);
                Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                Activity_StudentManager.this.students.clear();
                Activity_StudentManager.this.students.addAll(databaseStudent.allSinhvien());
                Setadapter();
                reset();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stt.setText("ENTER INFOMATION !");
                ten.setText("");
                sdt.setText("");
                email.setText("");
            }
        });
    }

    private void showdialog(int position) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.deletestudent);
        Button xoa = (Button) dialog.findViewById(R.id.btn_xoa);
        Button huy = (Button) dialog.findViewById(R.id.btn_huysv);
        dialog.show();
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(position);
                Toast.makeText(getApplicationContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    private void delete(int position) {
        Students students = this.students.get(position);
        databaseStudent.Delete(students.getId());
        this.students.clear();
        this.students.addAll(databaseStudent.allSinhvien());
        customAdapterStudent.notifyDataSetChanged();
    }

    private void setadapterIDlop() {
        DatabaseClass databaseClass = new DatabaseClass(Activity_StudentManager.this);
        lopList = databaseClass.Alllop();
        ArrayAdapter<Classroom> lopArrayAdapter = new ArrayAdapter<Classroom>(this, android.R.layout.simple_spinner_dropdown_item
                , databaseClass.Alllop());
        lislop.setAdapter(lopArrayAdapter);
    }

    public void dumy() {
        ten = findViewById(R.id.edt_ten);
        sdt = findViewById(R.id.edt_sdt);
        email = findViewById(R.id.edt_email);
        reset = findViewById(R.id.btn_reset);
        save = findViewById(R.id.btn_save);
        lislop = findViewById(R.id.snp_lop);
        listView = findViewById(R.id.lv_sv);
        update = findViewById(R.id.btn_update);
        stt = findViewById(R.id.edt_stt);
    }

    private void reset() {
        stt.setText("ENTER INFOMATION !");
        ten.setText("");
        sdt.setText("");
        email.setText("");
    }

    private void Setadapter() {
        if (customAdapterStudent == null) {
            customAdapterStudent = new CustomAdapterStudent(this, R.layout.item_sinhvien, students);
        }
        listView.setAdapter(customAdapterStudent);
        listView.setSelection(customAdapterStudent.getCount() - 1);
    }


}
