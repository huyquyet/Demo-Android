package com.example.framgianguyenhuyquyet.demo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.framgianguyenhuyquyet.demo.R;

public class CreateAuthorActivity extends AppCompatActivity {
    Button btn_xoaTrang, btn_luuTacGia;
    EditText edit_maTacGia, edit_tenTacGia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_author);
//        android.support.v7.app.ActionBar ABC = getSupportActionBar();
//        try {
//            if (ABC != null) {
//                ABC.hide();
//            }
//        } catch (Exception ex) {
//
//        }
        edit_maTacGia = (EditText) findViewById(R.id.edit_maTacGia);
        edit_tenTacGia = (EditText) findViewById(R.id.edit_tenTacGia);
        btn_luuTacGia = (Button) findViewById(R.id.btn_luuTacGia);
        btn_xoaTrang = (Button) findViewById(R.id.btn_xoaTrang);

        btn_xoaTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_maTacGia.setText("");
                edit_tenTacGia.setText("");
                edit_maTacGia.requestFocus();
            }
        });

        btn_luuTacGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putString("firstname", edit_maTacGia.getText().toString());
                bundle.putString("lastname", edit_tenTacGia.getText().toString());
                intent.putExtra("DATA_AUTHOR", bundle);
                setResult(SqliteActivity.INSERT_AUTHOR_SUCCESS, intent);
                finish();
            }
        });
    }
}
