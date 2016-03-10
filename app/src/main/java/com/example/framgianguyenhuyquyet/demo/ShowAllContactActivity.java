package com.example.framgianguyenhuyquyet.demo;

import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowAllContactActivity extends AppCompatActivity {
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_contact);
        btn_back = (Button) findViewById(R.id.btnback);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                ;
            }
        });
        showAllContact();
    }

    public void showAllContact() {
        Uri uri = Uri.parse("content://contacts/people");
        ArrayList<String> list = new ArrayList<String>();
        CursorLoader loader = new CursorLoader(ShowAllContactActivity.this, uri, null, null, null, null);
        Cursor cursor = loader.loadInBackground();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String s = "";
            String idColumnName = ContactsContract.Contacts._ID;
            int idIndex = cursor.getColumnIndex(idColumnName);
            s = cursor.getString(idIndex) + " - ";
            String nameColumnName = ContactsContract.Contacts.DISPLAY_NAME;
            int nameIndex = cursor.getColumnIndex(nameColumnName);
            s += cursor.getString(nameIndex);
            cursor.moveToNext();
            list.add(s);
        }
        cursor.close();
        ListView lv = (ListView) findViewById(R.id.listView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ShowAllContactActivity.this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
    }
}
