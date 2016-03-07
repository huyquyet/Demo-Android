package com.example.framgianguyenhuyquyet.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginSuccessActivity extends AppCompatActivity {
    Button btn_thoat;
    TextView textView_Msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        textView_Msg = (TextView) findViewById(R.id.txtmsg);
        btn_thoat = (Button) findViewById(R.id.btnThoat);
        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        textView_Msg.setText(bundle.getString("user"));

    }
}
