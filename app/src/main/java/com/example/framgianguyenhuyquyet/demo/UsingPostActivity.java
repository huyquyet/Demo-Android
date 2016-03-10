package com.example.framgianguyenhuyquyet.demo;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class UsingPostActivity extends AppCompatActivity {
    Button btn_1;
    EditText editText_1;
    ListView lv_1;
    Handler handler;
    AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    ArrayList<Integer> arrayList = new ArrayList<Integer>();
    ArrayAdapter<Integer> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_post);
        btn_1 = (Button) findViewById(R.id.btn_click);
        editText_1 = (EditText) findViewById(R.id.edit_number_1);
        lv_1 = (ListView) findViewById(R.id.listView_1);

        adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, arrayList);
        lv_1.setAdapter(adapter);
        handler = new Handler();
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doStart();
            }
        });
    }

    private void doStart() {
        final int nhan = Integer.parseInt(editText_1.getText().toString());
        final Random random = new Random();
        atomicBoolean.set(false);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < nhan && atomicBoolean.get(); i++) {
                    SystemClock.sleep(200);
                    // Post data ra ngoai Thread Main
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            int abc = random.nextInt(1000);
                            arrayList.add(abc);
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
                // het vong lap. gui 1 message thong bao ket thuc
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(UsingPostActivity.this, "Finish !", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        atomicBoolean.set(true);
        thread.start();
    }
}
