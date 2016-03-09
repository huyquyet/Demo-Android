package com.example.framgianguyenhuyquyet.demo.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.framgianguyenhuyquyet.demo.Adapter.MySpinnerAdapter;
import com.example.framgianguyenhuyquyet.demo.R;
import com.example.framgianguyenhuyquyet.demo.models.Author;
import com.example.framgianguyenhuyquyet.demo.models.Books;

import java.util.ArrayList;
import java.util.Calendar;

public class InsertBookActivity extends AppCompatActivity {

    Button btn_insertBook, btn_date, btn_back;
    EditText edit_tenSach, editTextDate;
    Spinner spinner1;
    ListView listViewBook;

    SQLiteDatabase database = null;
    int day, month, year;
    ArrayList<Author> authorArrayList = new ArrayList<Author>();
    ArrayList<Books> bookrrayList = new ArrayList<Books>();
    MySpinnerAdapter adapter = null;
    ArrayAdapter<Books> adapterlistBook = null;

    Books bookSelect = null;
    Author authorSelect = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_book);
        getControl();
        getdataSpinner();
        clickEvent();
    }

    private void getControl() {
        btn_insertBook = (Button) findViewById(R.id.btn_insertBook);
        btn_date = (Button) findViewById(R.id.btn_date);
        btn_back = (Button) findViewById(R.id.btn_back);
        edit_tenSach = (EditText) findViewById(R.id.edit_tenSach);
        editTextDate = (EditText) findViewById(R.id.editTextDate);
        // set Read only edit text
        editTextDate.setFocusable(false);

        spinner1 = (Spinner) findViewById(R.id.spinner1);


        listViewBook = (ListView) findViewById(R.id.listViewBook);
        adapterlistBook = new ArrayAdapter<Books>(InsertBookActivity.this, android.R.layout.simple_list_item_1, bookrrayList);
        listViewBook.setAdapter(adapterlistBook);

        setDefaultNXB();
        registerForContextMenu(listViewBook);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu_list_book, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete_book:
                deleteBook(bookSelect);
                break;
            case R.id.menu_edit_book:
                editBook();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void getdataSpinner() {
        authorArrayList = new ArrayList<Author>();
        Author author = new Author();
        author.setId("");
        author.setFirstname("Show all");
        author.setLastname("");
        authorArrayList.add(author);

        database = openOrCreateDatabase("mydata.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        if (database != null) {
            Cursor cursor = database.query("tblAuthor", null, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Author author1 = new Author();
                author1.setId(cursor.getString(0));
                author1.setFirstname(cursor.getString(1));
                author1.setLastname(cursor.getString(2));
                authorArrayList.add(author1);
                cursor.moveToNext();
            }
            cursor.close();
        }
        adapter = new MySpinnerAdapter(InsertBookActivity.this, R.layout.custum_list_author, authorArrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

    }

    private void clickEvent() {
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    authorSelect = null;
                    loadAllBook();
                    Toast.makeText(InsertBookActivity.this, "Null", Toast.LENGTH_LONG).show();
                } else {
                    authorSelect = authorArrayList.get(position);
                    loadBook(authorSelect.getId());
                    Toast.makeText(InsertBookActivity.this, "1", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                authorSelect = null;
                Toast.makeText(InsertBookActivity.this, "No thing", Toast.LENGTH_LONG).show();
            }
        });

        btn_insertBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (authorSelect == null) {
                    Toast.makeText(InsertBookActivity.this, "Please choose an author to insert", Toast.LENGTH_LONG).show();
                } else {
                    String name_book = edit_tenSach.getText().toString();
                    String date = editTextDate.getText().toString();
                    if (!name_book.equals("") && !date.equals("")) {
                        ContentValues values = new ContentValues();
                        values.put("title", name_book);
                        values.put("dateadded", date);
                        values.put("author_id", authorSelect.getId());
                        long check = database.insert("tblBooks", null, values);
                        if (check > 0) {
                            loadBook(authorSelect.getId());
                            edit_tenSach.setText("");
                            edit_tenSach.requestFocus();
                            Toast.makeText(InsertBookActivity.this, "Insert book success !", Toast.LENGTH_LONG).show();

                        } else
                            Toast.makeText(InsertBookActivity.this, "Insert book Fail!", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateEditText();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateEditText();
            }
        });

        listViewBook.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                bookSelect = bookrrayList.get(position);
                return false;
            }
        });
    }

    private void loadAllBook() {
//        database = openOrCreateDatabase("mydata.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        if (database != null) {
            bookrrayList.clear();
            Cursor cursor = database.query("tblBooks", null, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Books book = new Books(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                cursor.moveToNext();
                bookrrayList.add(book);
            }
            cursor.close();
        }
        adapterlistBook.notifyDataSetChanged();
    }

    private void loadBook(String authorID) {
        if (database != null) {
            bookrrayList.clear();
            Cursor cursor = database.query("tblBooks", null, "author_id=?", new String[]{authorID}, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Books book = new Books(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                bookrrayList.add(book);
                cursor.moveToNext();
            }
            cursor.close();
        }
        adapterlistBook.notifyDataSetChanged();
    }

    private void setDefaultNXB() {
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        editTextDate.setText(String.valueOf(day + " / " + (month + 1) + " / " + year));
    }

    private void setDateEditText() {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                editTextDate.setText(String.valueOf((dayOfMonth) + "/" + (monthOfYear + 1) + "/" + (year)));
            }
        };
        String date = editTextDate.getText().toString();
        String[] strArr = date.split("/");
        int ngay = Integer.parseInt(strArr[0].trim());
        int thang = Integer.parseInt(strArr[1].trim()) - 1;
        int nam = Integer.parseInt(strArr[2].trim());
        DatePickerDialog pic = new DatePickerDialog(InsertBookActivity.this, callback, nam, thang, ngay);
        pic.setTitle("Choose date !");
        pic.show();
    }


    private void deleteBook(Books book) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setMessage("");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (database != null) {
                    long check = database.delete("tblBooks", "id=?", new String[]{bookSelect.getId()});
                    if (check > 0) {
                        Toast.makeText(InsertBookActivity.this, "Delete " + bookSelect.getTitle() + " success !", Toast.LENGTH_LONG).show();
                        if (authorSelect != null) {
                            loadBook(authorSelect.getId());
                            bookSelect = null;
                        } else
                            loadAllBook();
                    }
                }
            }
        });
        builder.create().show();
    }

    private void editBook() {
    }
}
