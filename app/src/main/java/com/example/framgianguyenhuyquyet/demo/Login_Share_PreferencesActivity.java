package com.example.framgianguyenhuyquyet.demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Login_Share_PreferencesActivity extends AppCompatActivity {
    EditText editText_user, editText_pass;
    CheckBox check_luu;
    Button btn_dangNhap;
    String prefname = "my_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__share__preferences);
        getControl();
        clickEvent();
    }

    private void getControl() {
        editText_user = (EditText) findViewById(R.id.editText_user);
        editText_pass = (EditText) findViewById(R.id.editText_pass);
        check_luu = (CheckBox) findViewById(R.id.checkbox_luu);
        btn_dangNhap = (Button) findViewById(R.id.btn_dangNhap);
    }

    private void clickEvent() {
        btn_dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }

    private void doLogin() {
        finish();
        Intent intent = new Intent(this, LoginSuccessActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("user", editText_user.getText().toString());
        intent.putExtra("data", bundle);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        savingSharePreferences();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoringSharePreferences();
    }

    private void savingSharePreferences() {
        SharedPreferences preferences = getSharedPreferences(prefname, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String user = editText_user.getText().toString();
        String pass = editText_pass.getText().toString();
        boolean check = check_luu.isChecked();
        if (check) {
            editor.putString("user", user);
            editor.putString("pass", pass);
            editor.putBoolean("checked", check);
        } else {
            editor.clear();
        }
        // luu file
        editor.apply();
    }

    private void restoringSharePreferences() {
        SharedPreferences preferences = getSharedPreferences(prefname, MODE_PRIVATE);
        boolean check = preferences.getBoolean("checked", false);
        if (check) {
            editText_user.setText(preferences.getString("user", ""));
            editText_pass.setText(preferences.getString("pass", ""));
        }
        check_luu.setChecked(check);
    }
}
