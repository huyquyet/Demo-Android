package com.example.framgianguyenhuyquyet.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn_arraylist_listview, btn_arraylist_listview_object, btn_list_activity_listview;
    ListView lv;
    TextView txtSelection;
    EditText editNhap;
    ArrayList<String> danhsach = null;
    ArrayAdapter<String> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getControl();
        click();
    }

    private void getControl() {
        btn_arraylist_listview = (Button) findViewById(R.id.btn_arraylist_listview);
        btn_list_activity_listview = (Button) findViewById(R.id.btn_list_activity_listview);
        btn_arraylist_listview_object = (Button) findViewById(R.id.btn_arraylist_listview_object);
    }

    private void click() {
        btn_arraylist_listview.setOnClickListener(new myEventClick());
        btn_list_activity_listview.setOnClickListener(new myEventClick());
        btn_arraylist_listview_object.setOnClickListener(new myEventClick());
    }

    public class myEventClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_arraylist_listview:
                    Intent intent = new Intent(MainActivity.this, ArrayList_ListView.class);
                    startActivity(intent);
                    break;
                case R.id.btn_arraylist_listview_object:
                    Intent intent1 = new Intent(MainActivity.this, ArrayList_ListView_Object.class);
                    startActivity(intent1);
                    break;
                case R.id.btn_list_activity_listview:
                    Intent intent2 = new Intent(MainActivity.this, ArrayList_ListView.class);
                    startActivity(intent2);
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
