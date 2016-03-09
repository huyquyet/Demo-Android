package com.example.framgianguyenhuyquyet.demo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.framgianguyenhuyquyet.demo.R;
import com.example.framgianguyenhuyquyet.demo.models.Author;

import java.util.ArrayList;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 09/03/2016.
 */
public class MySpinnerAdapter extends ArrayAdapter {
    Activity context;
    int resource;
    ArrayList<Author> arrayList;

    public MySpinnerAdapter(Context context, int resource, ArrayList<Author> arrayList) {
        super(context, resource, arrayList);
        this.context = (Activity) context;
        this.resource = resource;
        this.arrayList = arrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(resource, null);
        TextView row1 = (TextView) convertView.findViewById(R.id.stt);
        TextView row2 = (TextView) convertView.findViewById(R.id.id_author);
        TextView row3 = (TextView) convertView.findViewById(R.id.id_name);

        Author author = arrayList.get(position);
        row1.setText(String.valueOf(position + 1));
        row2.setText(author.getId());
        row3.setText(author.toString());
        return convertView;

    }
}
