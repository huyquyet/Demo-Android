package com.example.framgianguyenhuyquyet.demo.Activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.framgianguyenhuyquyet.demo.Adapter.ListAuthorArrayAdapter;
import com.example.framgianguyenhuyquyet.demo.R;
import com.example.framgianguyenhuyquyet.demo.models.Author;

import java.util.ArrayList;

public class ShowListAuthorActivity extends AppCompatActivity {
    SQLiteDatabase database = null;
    Cursor cursor = null;
    ListAuthorArrayAdapter adapter = null;
    ArrayList<Author> arrayList = new ArrayList<Author>();
    Author authorSelect = null;

    ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_author);
        listView = (ListView) findViewById(R.id.lv_listAuthor);
        getEvent();
        clickEvent();
        registerForContextMenu(listView);
    }

    private void getData() {
        database = openOrCreateDatabase("mydata.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        if (database != null) {
            cursor = database.query("tblAuthor", null, null, null, null, null, null);
            startManagingCursor(cursor);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Author data = new Author();
                data.setId(cursor.getString(0));
                data.setFirstname(cursor.getString(1));
                data.setLastname(cursor.getString(2));
                arrayList.add(data);
                cursor.moveToNext();
            }
            cursor.close();
        }
    }

    private void getEvent() {
        getData();
        adapter = new ListAuthorArrayAdapter(this, R.layout.custum_list_author, arrayList);
        listView.setAdapter(adapter);
    }

    private void clickEvent() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                authorSelect = arrayList.get(position);
                Toast.makeText(ShowListAuthorActivity.this, "Choose Author " + authorSelect.toString(), Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu_list_author, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit_author:
                Intent intent = new Intent(ShowListAuthorActivity.this, EditAuthorActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("AUTHOR", authorSelect);
                intent.putExtra("DATA", bundle);
                startActivityForResult(intent, SqliteActivity.OPEN_ACTIVITY_EDIT_AUTHOR);
                break;
            case R.id.menu_delete_author:
                delete();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SqliteActivity.EDIT_AUTHOR_SUCCESS) {
//            Intent intent = data;
            Bundle bundle = data.getBundleExtra("DATA");
            Author author = (Author) bundle.getSerializable("AUTHOR");

            ContentValues values = new ContentValues();
            values.put("id", author.getId());
            values.put("firstname", author.getFirstname());
            values.put("lastname", author.getLastname());

            if (database != null) {
                int check = database.update("tblAuthor", values, "id=?", new String[]{author.getId()});
                if (check > 0) {
                    Toast.makeText(ShowListAuthorActivity.this, "Update success!", Toast.LENGTH_LONG).show();
                    arrayList.clear();
                    getData();
                    adapter.notifyDataSetChanged();
                    authorSelect = null;
                }
            }
        }
    }

    private void delete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ShowListAuthorActivity.this);
        builder.setTitle("Delete Author");
        builder.setMessage("Delete Author " + authorSelect.toString());
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (database != null) {
                    int check = database.delete("tblAuthor", "id = ?", new String[]{authorSelect.getId()});
                    if (check > 0) {
                        Toast.makeText(ShowListAuthorActivity.this, "Delete Author " + authorSelect.toString() + " success!", Toast.LENGTH_LONG).show();
                        arrayList.clear();
                        getData();
                        adapter.notifyDataSetChanged();
                        authorSelect = null;
                    }
                }
            }
        });
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
}
