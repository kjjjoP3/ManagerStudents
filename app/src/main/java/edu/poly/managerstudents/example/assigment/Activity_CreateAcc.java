package edu.poly.managerstudents.example.assigment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import edu.poly.managerstudents.R;
import edu.poly.managerstudents.example.database.DatabaseAcc;
import edu.poly.managerstudents.example.models.Account;

import java.util.List;

public class Activity_CreateAcc extends AppCompatActivity {
    private EditText user, pas1, pas2;
    private Button button;
    private CheckBox display;
    ListView listView;
    private DatabaseAcc databaseAcc = new DatabaseAcc(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__create_acc);
        user = findViewById(R.id.edt_usercr);
        pas1 = findViewById(R.id.edt_pascr);
        pas2 = findViewById(R.id.edt_pas2cr);
        display = findViewById(R.id.cb_display);


        display.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String mk = pas1.getText().toString();
                    String mk2 = pas2.getText().toString();
                    pas1.setText(mk);
                    pas2.setText(mk2);
                    pas1.setInputType(1);
                    pas2.setInputType(1);
                } else {
                    pas1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    pas2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    public void addacc(View view) {
        String u = user.getText().toString();
        String p1 = pas1.getText().toString();
        String p2 = pas2.getText().toString();
        try {
            if (u.isEmpty()) {
                user.setError("User là bắt buộc");
                return;
            }
            if (p1.isEmpty()) {
                pas1.setError("Password là bắt buộc");
                return;
            }
            if (p2.isEmpty()) {
                pas2.setError("Bạn phải xác nhận password");
                return;
            }
            if (p2.equals(p1) == false) {
                pas2.setError("Password bạn xác nhận không khớp");
                return;
            }
        } catch (Exception e) {
            Log.e("ERRO", e.toString());
        }
        Account account = new Account(u, p2);
        databaseAcc.addlop(account);
        Toast.makeText(getApplicationContext(), "Đã thêm tài khoản", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Activity_Login.class);
        startActivity(intent);

    }
}
