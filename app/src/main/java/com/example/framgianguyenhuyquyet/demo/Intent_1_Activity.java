package com.example.framgianguyenhuyquyet.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.framgianguyenhuyquyet.demo.models.data;

public class Intent_1_Activity extends AppCompatActivity {
    private data dulieu;
    EditText edit_1, edit_2, edit_3;
    Button btn_kq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_1_);
        getControl();
        btn_kq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });
    }

    private void getControl() {
        edit_1 = (EditText) findViewById(R.id.edit_text_1);
        edit_2 = (EditText) findViewById(R.id.edit_text_2);
        edit_3 = (EditText) findViewById(R.id.edit_text_3);
        btn_kq = (Button) findViewById(R.id.btn_ketqua);
    }

    private void sendData() {
        int a = Integer.parseInt(edit_1.getText().toString());
        int b = Integer.parseInt(edit_2.getText().toString());
        int c = Integer.parseInt(edit_3.getText().toString());
        dulieu = new data(a, b, c);
        Bundle bundle = new Bundle();
        Intent intent = new Intent(Intent_1_Activity.this, Intent_2_Activity.class);
        bundle.putSerializable("data", dulieu);
        intent.putExtra("data", bundle);
        startActivity(intent);
    }
}
