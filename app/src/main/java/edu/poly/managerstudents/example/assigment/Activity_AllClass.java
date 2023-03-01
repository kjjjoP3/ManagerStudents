package edu.poly.managerstudents.example.assigment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

;
import edu.poly.managerstudents.R;
import edu.poly.managerstudents.example.database.DatabaseClass;
import edu.poly.managerstudents.example.models.Classroom;

import java.util.List;

import Adapter.CustomApdaterClass;

public class Activity_AllClass extends AppCompatActivity {
    private EditText idlop, tenlop, stt;
    private Button save, reset, sua;
    private ListView lislop;
    private DatabaseClass databaseClass;
    private CustomApdaterClass customApdaterClass;
    private List<Classroom> lopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_class_);
        databaseClass = new DatabaseClass(this);
        lislop = findViewById(R.id.alll);
        lopList = databaseClass.Alllop();
        Setadapter();

    };

    private void Setadapter() {
        if (customApdaterClass == null) {
            customApdaterClass = new CustomApdaterClass(this, R.layout.item_class, lopList);
        }
        lislop.setAdapter(customApdaterClass);

    }


}
