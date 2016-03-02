package com.example.framgianguyenhuyquyet.demo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framgianguyenhuyquyet.demo.models.SmartPhone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 02/03/2016.
 */
public class MyArrayAdapter extends ArrayAdapter {
    Activity context;
    ArrayList<SmartPhone> arrayList;
    int resource;
    public MyArrayAdapter(Activity context, int resource, ArrayList<SmartPhone> objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrayList = objects;
        this.resource= resource;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(resource, null);

        if (arrayList.size()>0 && position >=0){
            final TextView textView = (TextView) convertView.findViewById(R.id.textView_name);
            final SmartPhone smartPhone = arrayList.get(position);
            textView.setText(smartPhone.toString());
            final ImageView imageView = (ImageView) convertView.findViewById(R.id.img_item);
            if(smartPhone.phanloai)
        }
        return convertView;
    }
}
