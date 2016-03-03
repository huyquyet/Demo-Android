package com.example.framgianguyenhuyquyet.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.framgianguyenhuyquyet.demo.models.data;

public class Intent_2_Activity extends AppCompatActivity {
    private data dulieu;
    TextView text_kq_so_1, text_kq_so_2, text_kq_so_3, text_kq;
    Button btn_back, btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_2_);
        getContorl();
        viewData();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                close();
            }
        });
    }

    private void getContorl() {
        text_kq_so_1 = (TextView) findViewById(R.id.text_kq_so_1);
        text_kq_so_2 = (TextView) findViewById(R.id.text_kq_so_2);
        text_kq_so_3 = (TextView) findViewById(R.id.text_kq_so_3);
        text_kq = (TextView) findViewById(R.id.text_kq);

        btn_back = (Button) findViewById(R.id.btn_back);
        btn_exit = (Button) findViewById(R.id.btn_exit);
    }

    private void viewData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        dulieu = (data) bundle.getSerializable("data");
        text_kq.setText(String.valueOf(dulieu.getA() + dulieu.getB() + dulieu.getC()));
        text_kq.setText(String.valueOf(dulieu.getA() + dulieu.getB() + dulieu.getC()));
        text_kq_so_1.setText(String.valueOf(dulieu.getA()));
        text_kq_so_2.setText(String.valueOf(dulieu.getB()));
        text_kq_so_3.setText(String.valueOf(dulieu.getC()));
    }

}
