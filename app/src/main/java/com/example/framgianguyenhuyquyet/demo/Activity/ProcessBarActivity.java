package com.example.framgianguyenhuyquyet.demo.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.framgianguyenhuyquyet.demo.Controller.MyAsyncTask;
import com.example.framgianguyenhuyquyet.demo.R;

public class ProcessBarActivity extends AppCompatActivity {
    Button btn_click;
    TextView textView_1;
    ProgressBar bar_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_bar);
        btn_click = (Button) findViewById(R.id.btn_click);
        textView_1 = (TextView) findViewById(R.id.textView_1);
        bar_1 = (ProgressBar) findViewById(R.id.progressBar_1);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsyncTask myAsyncTask = new MyAsyncTask(ProcessBarActivity.this);
                myAsyncTask.execute();
            }
        });
    }
}
