package com.example.framgianguyenhuyquyet.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.appevents.AppEventsLogger;
//import com.facebook.FacebookSdk;

public class ReadJsonActivity extends AppCompatActivity {
    TextView txtId;
    TextView txtFirstName;
    TextView txtLastName;
    TextView txtGender;
    TextView txtName;
    TextView txtLocale;
    TextView txtLink;
    TextView txtUserName;
    Button btnShowImage;

    public TextView findTextView(int id) {
        return (TextView) findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_json);
        getControl();
        clickEvent();
    }

    private void getControl() {
        txtId = findTextView(R.id.txtId);
        txtFirstName = findTextView(R.id.txtFirstName);
        txtLastName = findTextView(R.id.txtLastName);
        txtName = findTextView(R.id.txtName);
        txtLink = findTextView(R.id.txtLink);
        txtGender = findTextView(R.id.txtGender);
        txtLocale = findTextView(R.id.txtLocale);
        txtUserName = findTextView(R.id.txtUserName);
        btnShowImage = (Button) findTextView(R.id.btnShowImage);
    }

    private void clickEvent() {
        btnShowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }
}
