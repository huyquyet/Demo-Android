package com.example.framgianguyenhuyquyet.demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ExampleSettingActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    Button btn_mySetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_setting);
        btn_mySetting = (Button) findViewById(R.id.btn_mySetting);
        btn_mySetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExampleSettingActivity.this, MySettingActivity.class);
                startActivity(intent);
            }
        });
        Context context = getApplicationContext();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.registerOnSharedPreferenceChangeListener(ExampleSettingActivity.this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        boolean data = sharedPreferences.getBoolean(key, false);
        Toast.makeText(this, "checked = " + data, Toast.LENGTH_LONG).show();
    }
}
