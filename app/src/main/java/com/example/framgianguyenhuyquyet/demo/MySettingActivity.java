package com.example.framgianguyenhuyquyet.demo;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 07/03/2016.
 */
public class MySettingActivity extends PreferenceActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.mypreferenceslayout);
    }
}
