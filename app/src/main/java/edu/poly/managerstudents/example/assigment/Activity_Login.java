package edu.poly.managerstudents.example.assigment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import edu.poly.managerstudents.R;
import edu.poly.managerstudents.example.database.DatabaseAcc;

import java.util.List;

public class Activity_Login extends AppCompatActivity {
    private EditText user, pass;
    private Button reset, login, cre;
    private CheckBox display, remem;
    private DatabaseAcc databaseAcc = new DatabaseAcc(this);
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String USERNAME = "user";
    String PASSWORD = "password";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);
        user = findViewById(R.id.edt_user);
        pass = findViewById(R.id.edt_pass);
        reset = findViewById(R.id.btn_reset);
        login = findViewById(R.id.btn_login);
        display = findViewById(R.id.cb_rmb);
        remem = findViewById(R.id.ltk);
        cre = findViewById(R.id.btn_creacc);
        sharedPreferences = getSharedPreferences("loginPres", MODE_PRIVATE);
        user.setText(sharedPreferences.getString(USERNAME, ""));
        pass.setText(sharedPreferences.getString(PASSWORD, ""));


        display.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String mk = pass.getText().toString();
                    pass.setText(mk);
                    pass.setInputType(1);
                } else {
                    pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    public void login(View view) {
        String u = user.getText().toString();
        String p = pass.getText().toString();

        try {
            if (u.isEmpty()) {
                user.setError("User là bắt buộc");
                return;
            }
            if (p.isEmpty()) {
                pass.setError("PassWord là bắt buộc");
                return;
            }
            List userList = databaseAcc.userList();
            for (int i = 0; i < userList.size(); i++) {
                String te = userList.get(i).toString();
                if (u.equals(te)) {
                    List passlist = databaseAcc.passList();
                    for (int j = 0; j < passlist.size(); j++) {
                        String tes = passlist.get(j).toString();
                        if (p.equals(tes)) {
                            if (remem.isChecked()) {
                                editor = sharedPreferences.edit();
                                editor.putString(USERNAME, user.getText().toString().trim());
                                editor.putString(PASSWORD, pass.getText().toString());
                                editor.commit();
                            }
                            Intent intent = new Intent(this, Activity_main.class);
                            startActivity(intent);

                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.e("ERRO", e.toString());
        }
    }

    public void create(View view) {
        Intent intent = new Intent(this, Activity_CreateAcc.class);
        startActivity(intent);
    }

    public void reset(View view) {
        user.setText("");
        pass.setText("");
    }

}
