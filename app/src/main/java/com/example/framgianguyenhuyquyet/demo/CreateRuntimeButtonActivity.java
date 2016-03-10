package com.example.framgianguyenhuyquyet.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.concurrent.atomic.AtomicBoolean;

public class CreateRuntimeButtonActivity extends AppCompatActivity {
    Handler handleMain;
    AtomicBoolean atomicBoolean = null;
    LinearLayout layoutdevebutton;
    Button btnOk;
    EditText edtOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_runtime_button);
        layoutdevebutton = (LinearLayout) findViewById(R.id.layout_draw_button);
        btnOk = (Button) findViewById(R.id.btn_ok);
        edtOk = (EditText) findViewById(R.id.edit_number);

        handleMain = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                // Nhan nhan tu tien trinh con gui len
                String nhan = msg.obj.toString();
                // Khoi tao 1 Button
                Button b = new Button(CreateRuntimeButtonActivity.this);
                b.setText(nhan);
                LinearLayout.LayoutParams params = new
                        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                b.setLayoutParams(params);
                layoutdevebutton.addView(b);
            }
        };
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doStart();
            }
        });
    }

    private void doStart() {
        atomicBoolean = new AtomicBoolean(false);
        final int soButton = Integer.parseInt(edtOk.getText().toString());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < soButton && atomicBoolean.get(); i++) {
                    SystemClock.sleep(1);
                    Message msg = handleMain.obtainMessage();
                    msg.obj = "Button : " + i;
                    handleMain.sendMessage(msg);
                }
            }
        });
        atomicBoolean.set(true);
        thread.start();
    }
}
