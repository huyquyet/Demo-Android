package com.example.framgianguyenhuyquyet.demo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.framgianguyenhuyquyet.demo.R;
import com.example.framgianguyenhuyquyet.demo.models.Author;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 08/03/2016.
 */
public class EditAuthorActivity extends AppCompatActivity {
    Button btn_xoaTrang, btn_luuTacGia;
    EditText edit_maTacGia, edit_tenTacGia;
    Author author = null;
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_author);
        getControl();
        getData();
        clickEvent();
    }

    private void getControl() {
        edit_maTacGia = (EditText) findViewById(R.id.edit_maTacGia);
        edit_tenTacGia = (EditText) findViewById(R.id.edit_tenTacGia);
        btn_luuTacGia = (Button) findViewById(R.id.btn_luuTacGia);
        btn_xoaTrang = (Button) findViewById(R.id.btn_xoaTrang);

    }

    private void getData() {
        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("DATA");
        author = (Author) bundle.getSerializable("AUTHOR");
        edit_maTacGia.setText(author.getFirstname());
        edit_tenTacGia.setText(author.getLastname());
    }

    private void clickEvent() {
        btn_xoaTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_maTacGia.setText(author.getFirstname());
                edit_tenTacGia.setText(author.getLastname());
                edit_maTacGia.requestFocus();
            }
        });
        btn_luuTacGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                author.setFirstname(edit_maTacGia.getText().toString());
                author.setLastname(edit_tenTacGia.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putSerializable("AUTHOR", author);
                intent.putExtra("DATA", bundle);
                setResult(SqliteActivity.EDIT_AUTHOR_SUCCESS, intent);
                EditAuthorActivity.this.finish();
            }
        });
    }
}
