package com.example.framgianguyenhuyquyet.demo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framgianguyenhuyquyet.demo.models.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 01/03/2016.
 */
public class MyArrayAdapter extends ArrayAdapter<Employee> {
    Activity context;
    ArrayList<Employee> myArray;
    int layoutId;

    //    Khoi tao contrustor
    public MyArrayAdapter(Activity context, int layoutId, ArrayList<Employee> arr) {
        super(context, layoutId, arr);
        this.context = context;
        this.myArray = arr;
        this.layoutId = layoutId;
    }

    /**
     * Custom layout
     *
     * @param position:    vi tri cua phan tu trong danh sach
     * @param convertView: dung convertView de xu ly Item
     * @param parent:      danh sach nhan vien
     * @return View
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);

        if (myArray.size() > 0 && position >= 0) {
            final TextView txt_ten = (TextView) convertView.findViewById(R.id.txt_name);
            final Employee employee = myArray.get(position);
            txt_ten.setText(employee.toString());
            final ImageView imageView = (ImageView) convertView.findViewById(R.id.imgitem);
            if (employee.isGender()) {
                imageView.setImageResource(R.drawable.girlicon);
            } else {
                imageView.setImageResource(R.drawable.boyicon);
            }
        }
        return convertView;
    }
}
