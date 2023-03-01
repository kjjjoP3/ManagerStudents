package edu.poly.managerstudents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.poly.managerstudents.controller.LopController;
import edu.poly.managerstudents.model.Lop;

public class ThemLopActivity extends AppCompatActivity {

    EditText txtMaLop, txtTenLop;
    Button btnThem, btnXem;
    LopController lopController;
    ListView listView;
    String textItem, subTextItem;
    List<String> list;

    int m_position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_lop);
        txtMaLop = findViewById(R.id.edMaLop);
        txtTenLop = findViewById(R.id.edTenLop);
        btnThem = findViewById(R.id.btnThemLop);
        btnXem = findViewById(R.id.btnXemLop);
        listView = findViewById(R.id.lv);
        registerForContextMenu(listView);
        lopController = new LopController(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textItem = (String)listView.getItemAtPosition(position); //lay ve text
                subTextItem = textItem.substring(0,textItem.indexOf(" "));//lay ma lop
                m_position = position; // Lưu lại vị trí được chọn
            }
        });

        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                textItem = (String)listView.getItemAtPosition(position); //lay ve text
                subTextItem = textItem.substring(0,textItem.indexOf(" "));//lay ma lop
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE,0,0,"Xoa");
        menu.add(Menu.NONE,1,0,"Sua");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId()){
            case 0:
                if (subTextItem == null) {
                    Toast.makeText(ThemLopActivity.this,"Khong tim thay ma lop", Toast.LENGTH_LONG).show();
                    break;
                }
                Toast.makeText(ThemLopActivity.this,"Context ID: "+item.getItemId()+"+Ma Lop: "
                        + subTextItem, Toast.LENGTH_LONG).show();
                try {
                    lopController.deleteLop(subTextItem);
                    list.remove(m_position);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ThemLopActivity.this, android.R.layout.simple_list_item_1,list);
                    listView.setAdapter(adapter);
                    listView.deferNotifyDataSetChanged();
                    Toast.makeText(ThemLopActivity.this,"xoa duoc", Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(ThemLopActivity.this,"Khong xoa duoc", Toast.LENGTH_LONG).show();
                }
                break;
            case 1:
                if (subTextItem == null) {
                    Toast.makeText(ThemLopActivity.this,"Khong tim thay ma lop", Toast.LENGTH_LONG).show();
                    break;
                }
                Toast.makeText(ThemLopActivity.this,"Context ID: "+item.getItemId()+"+Ma Lop: "
                        + subTextItem, Toast.LENGTH_LONG).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void xemLop(View view){
        list = new ArrayList<String>();
        list = lopController.getAllLopString();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ThemLopActivity.this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        listView.deferNotifyDataSetChanged();
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