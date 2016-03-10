package com.example.framgianguyenhuyquyet.demo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.framgianguyenhuyquyet.demo.Controller.FibonacciAsyncTask;
import com.example.framgianguyenhuyquyet.demo.R;

public class FibonacciActivity extends AppCompatActivity {
    Button btn_click;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci);
        final FibonacciAsyncTask fibo = new FibonacciAsyncTask(FibonacciActivity.this);
        btn_click = (Button) findViewById(R.id.btn_nhap);
        editText = (EditText) findViewById(R.id.editView_1);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fibo.execute(Integer.parseInt(editText.getText().toString()));
            }
        });
    }
}
