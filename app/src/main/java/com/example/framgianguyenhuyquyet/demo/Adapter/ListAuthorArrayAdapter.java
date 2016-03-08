package com.example.framgianguyenhuyquyet.demo.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.framgianguyenhuyquyet.demo.R;
import com.example.framgianguyenhuyquyet.demo.models.Author;

import java.util.ArrayList;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 08/03/2016.
 */
public class ListAuthorArrayAdapter extends ArrayAdapter {
    Activity context;
    int resource;
    ArrayList<Author> arrayList;

    public ListAuthorArrayAdapter(Activity context, int resource, ArrayList<Author> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.resource = resource;
        this.arrayList = arrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(resource, null);

        TextView stt = (TextView) convertView.findViewById(R.id.stt);
        TextView id_author = (TextView) convertView.findViewById(R.id.id_author);
        TextView name_author = (TextView) convertView.findViewById(R.id.id_name);
        Author author = arrayList.get(position);
        stt.setText(String.valueOf(position + 1));
        id_author.setText(String.valueOf("author" + position));
        name_author.setText(author.toString());
        return convertView;
    }
}
