package com.example.framgianguyenhuyquyet.demo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.framgianguyenhuyquyet.demo.models.Student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.SimpleFormatter;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 02/03/2016.
 */
public class MyArrayAdapter extends ArrayAdapter<Student> {
    Activity context;
    ArrayList<Student> arrayList;
    int resource;

    public MyArrayAdapter(Activity context, int resource, ArrayList<Student> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.arrayList = arrayList;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(resource, null);

        if (arrayList.size() > 0 && position >= 0) {
            final TextView textView = (TextView) convertView.findViewById(R.id.txtMaVaTen);
            final TextView textView1 = (TextView) convertView.findViewById(R.id.txtThongTinKhac);
//            final CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox_chonSV);
            Student student = arrayList.get(position);
            textView.setText(String.valueOf(student.getId() + '-' + student.getName()));
            SimpleDateFormat simpleFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            textView1.setText(String.valueOf((student.isGender() ? "Nữ-" : "Nam-") + simpleFormatter.format(student.getBrithday()) + student.getPlaceOfBirth()));
//            checkBox.setChecked(false);
        }
        return convertView;
    }
}
