package com.example.framgianguyenhuyquyet.demo.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.framgianguyenhuyquyet.demo.R;

import java.util.Locale;

public class SqliteActivity extends AppCompatActivity {
    public static final int OPEN_ACTIVITY_INSERT_AUTHOR = 1;
    public static final int INSERT_AUTHOR_SUCCESS = 2;
    public static final int OPEN_ACTIVITY_EDIT_AUTHOR = 3;
    public static final int EDIT_AUTHOR_SUCCESS = 4;
    private SQLiteDatabase database = null;

    Button btn_themTacGia, btn_xemDanhSachTacGia, btn_quanLySach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
//        android.support.v7.app.ActionBar AB = getSupportActionBar();
//        assert AB != null;
//        AB.hide();
        insertDataBase();
        getControl();
        clickEvent();
    }

    /**
     * Kiem tra xem da co dataBase chua
     * - chua co them moi
     * - co roi thoi
     */
    private SQLiteDatabase insertDataBase() {

        try {
            /**
             * Mo CSDL neu ko co thi tao moi
             */
            database = openOrCreateDatabase("mydata.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            if (database != null) {
                if (isDatabaseExists(database, "tblAuthor")) {
                    return database;
                }
                /**
                 * ko ton tai bang => tao moi CSDL trong
                 * bat dau tao moi cac bang
                 */
                database.setLocale(Locale.getDefault());
                database.setVersion(1);

                /**
                 * Create table tblAuthor
                 */
                String sqlAuthor = "create table tblAuthor (" +
                        "id integer primary key autoincrement, " +
                        "firstname text, " +
                        "lastname text)";
                database.execSQL(sqlAuthor);

                /**
                 * Create table tblBook
                 */
                String sqlBook = "create table tblBooks(" +
                        "id integer primary key autoincrement," +
                        "title text" + "dateadded text)";
                database.execSQL(sqlBook);

                /**
                 * Create Trigger
                 */
                String sqlTrigger = "create trigger fk_insert_book before insert on tblBooks "
                        + " for each row "
                        + " begin "
                        + " select raise(rollback,'them du lieu tren bang tblBooks bi sai') "
                        + " where (select id from tblAuthors where id=new.authorid) is null ;"
                        + " end;";
                database.execSQL(sqlTrigger);
                Toast.makeText(this, "Create dataBase success", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {

        }
        return database;
    }

    private boolean isDatabaseExists(SQLiteDatabase database, String tableName) {
        Cursor cursor = database.rawQuery("Select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    private void getControl() {
        btn_themTacGia = (Button) findViewById(R.id.btn_themTacGia);
        btn_xemDanhSachTacGia = (Button) findViewById(R.id.btn_xemDanhSachTacGia);
        btn_quanLySach = (Button) findViewById(R.id.btn_quanLySach);
    }

    private void clickEvent() {
        btn_themTacGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SqliteActivity.this, CreateAuthorActivity.class);
                startActivityForResult(intent, OPEN_ACTIVITY_INSERT_AUTHOR);
            }
        });

        btn_xemDanhSachTacGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SqliteActivity.this, ShowListAuthorActivity.class);
                startActivity(intent);
            }
        });
        btn_quanLySach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == INSERT_AUTHOR_SUCCESS) {
            Bundle bundle = data.getBundleExtra("DATA_AUTHOR");
            String firstname = bundle.getString("firstname");
            String lastname = bundle.getString("lastname");
            ContentValues values = new ContentValues();
            values.put("firstname", firstname);
            values.put("lastname", lastname);
            if (database != null) {
                long check = database.insert("tblAuthor", null, values);
                if (check == -1)
                    Toast.makeText(this, "Error insert new Author", Toast.LENGTH_LONG).show();
                else {
                    Toast.makeText(this, "Insert success new Author", Toast.LENGTH_LONG).show();
                    values.clear();
                }
            } else
                values.clear();

        }
    }
}
