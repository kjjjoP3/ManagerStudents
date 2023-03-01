package edu.poly.managerstudents.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import edu.poly.managerstudents.R;
import edu.poly.managerstudents.example.models.Students;

import java.util.List;

public class CustomAdapterStudent extends ArrayAdapter<Students> {
    private Context context;
    private int resoult;
    private List<Students> studentsList;
    public CustomAdapterStudent(@NonNull Context context, int resource, @NonNull List<Students> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resoult=resource;
        this.studentsList =objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Viewholder viewholder ;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_sinhvien,parent,false);
            viewholder=new Viewholder();
            viewholder.tv_id=convertView.findViewById(R.id.tv_id);
            viewholder.tv_n=convertView.findViewById(R.id.tv_nganh);
            viewholder.tv_ten=convertView.findViewById(R.id.tv_ten);

            viewholder.tv_email=convertView.findViewById(R.id.tv_email);
            viewholder.tv_sdt=convertView.findViewById(R.id.tv_sdt);

            convertView.setTag(viewholder);
        }else{
            viewholder= (Viewholder) convertView.getTag();
        }
        Students students = studentsList.get(position);
        viewholder.tv_id.setText(String.valueOf(students.getId()));
        viewholder.tv_ten.setText(students.getName());
        viewholder.tv_n.setText(students.getNganh());
        viewholder.tv_email.setText(students.getEmail());
        viewholder.tv_sdt.setText(students.getSdt());

        return convertView;
    }
    public class Viewholder{
        TextView tv_id;
        TextView tv_n;
        TextView tv_ten;
        TextView tv_sdt;
        TextView tv_email;
    }
}
