package com.example.framgianguyenhuyquyet.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

public class ProgressBarActivity extends AppCompatActivity {
    ProgressBar bar, bar2;
    Handler handler;
    AtomicBoolean isrunning = new AtomicBoolean(false);
    Button btnstart, btnstart2;
    TextView lblmsg, lblmsg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        bar = (ProgressBar) findViewById(R.id.progressBar1);
        bar2 = (ProgressBar) findViewById(R.id.progressBar2);
        btnstart = (Button) findViewById(R.id.btnstart);
        btnstart2 = (Button) findViewById(R.id.btnstart2);
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doStart();
            }
        });
        btnstart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doStart2();
            }
        });
        handler = new Handler() {
            public void handleMessage(Message mgs) {
                super.handleMessage(mgs);
                bar.setProgress(mgs.arg1);
                lblmsg.setText(String.valueOf(mgs.arg1));
                bar2.setProgress(mgs.arg2);
                lblmsg2.setText(String.valueOf(mgs.arg2));
            }
        };
        lblmsg = (TextView) findViewById(R.id.textView1);
        lblmsg2 = (TextView) findViewById(R.id.textView2);
    }

    public void doStart() {
        bar.setProgress(0);
//        isrunning.set(false);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    SystemClock.sleep(100);
                    Message msg = handler.obtainMessage();
                    msg.arg1 = i;
                    handler.sendMessage(msg);
                }
            }
        });
//        isrunning.set(true);
        thread.start();
    }

    public void doStart2() {
        bar.setProgress(0);
//        isrunning.set(false);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    SystemClock.sleep(1000);
                    Message msg = handler.obtainMessage();
                    msg.arg2 = i;
                    handler.sendMessage(msg);
                }
            }
        });
//        isrunning.set(true);
        thread.start();
    }
}
