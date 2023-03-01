package edu.poly.managerstudents.example.assigment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

;
import edu.poly.managerstudents.R;
import edu.poly.managerstudents.example.database.DatabaseClass;
import edu.poly.managerstudents.example.models.Classroom;

import java.util.List;

import Adapter.CustomApdaterClass;

public class Activity_ManagerClass extends AppCompatActivity {
    private EditText idlop, tenlop, stt;
    private Button save, reset, sua;
    private ListView lislop;
    private DatabaseClass databaseClass;
    private CustomApdaterClass customApdaterClass;
    private List<Classroom> lopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_manager);
        databaseClass = new DatabaseClass(this);
        Dumy();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stt.setText("NHẬP THÔNG TIN");
                idlop.setText("");
                tenlop.setText("");
            }
        });

        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Classroom lop = new Classroom();
                lop.setId(Integer.parseInt(stt.getText().toString()));
                lop.setIdlop(idlop.getText().toString());
                lop.setTenlop(tenlop.getText().toString());
                int result = databaseClass.Update(lop);
                if (result > 0) {
                    lopList.clear();
                    lopList.addAll(databaseClass.Alllop());
                    customApdaterClass.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "UPDATE THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                    save.setEnabled(true);
                    sua.setEnabled(false);
                    reset.setEnabled(true);
                }
            }
        });
        lislop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Classroom lop = lopList.get(position);
                stt.setText(String.valueOf(lop.getId()));
                idlop.setText(lop.getIdlop());
                tenlop.setText(lop.getTenlop());
                stt.setEnabled(false);
                save.setEnabled(false);
                reset.setEnabled(true);
                sua.setEnabled(true);
            }
        });
        lislop.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                showdialog(position);
                return false;
            }
        });
        lopList = databaseClass.Alllop();
        Setadapter();
        save.setOnClickListener(v -> {
            String idl = idlop.getText().toString();
            String tl = tenlop.getText().toString();
            if (idl.isEmpty()) {
                idlop.setError("ID lớp không được trống");
                return;
            }
            if (tl.isEmpty()) {
                tenlop.setError("Tên lớp không được trống");
                return;
            } else {
                Classroom lop = new Classroom(idl, tl);
                if (lop != null) {
                    databaseClass.addlop(lop);
                    Reset();
                    Toast.makeText(getApplicationContext(), "Thêm thành công ", Toast.LENGTH_SHORT).show();
                    lopList.clear();
                    lopList.addAll(databaseClass.Alllop());
                    Setadapter();
                }
            }
        });
    }

    public void Reset() {
        idlop.setText("");
        tenlop.setText("");
    }

    private void Setadapter() {
        if (customApdaterClass == null) {
            customApdaterClass = new CustomApdaterClass(this, R.layout.item_class, lopList);
        }
        lislop.setAdapter(customApdaterClass);
        lislop.setSelection(customApdaterClass.getCount() - 1);
    }

    private void Dumy() {
        idlop = findViewById(R.id.edt_idlop);
        tenlop = findViewById(R.id.edt_tenlop);
        save = findViewById(R.id.btn_save);
        reset = findViewById(R.id.btn_reset);
        sua = findViewById(R.id.btn_update);
        lislop = findViewById(R.id.lis_dslop);
        stt = findViewById(R.id.edt_stt);
    }

    private void showdialog(int posotion) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.delete);
        Button dy = (Button) dialog.findViewById(R.id.btn_done);
        Button huy = (Button) dialog.findViewById(R.id.btn_huy);
        dialog.show();
        dy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dedete(posotion);
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

    private void dedete(int position) {
        Classroom lop = lopList.get(position);
        databaseClass.Delete(lop.getId());
        Toast.makeText(getApplicationContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
        save.setEnabled(true);
        lopList.clear();
        lopList.addAll(databaseClass.Alllop());
        customApdaterClass.notifyDataSetChanged();
    }

}
