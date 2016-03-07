package com.example.framgianguyenhuyquyet.demo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadXMLSAX extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_xmlsax);

        XmlPullParserFactory fc = null;
        XmlPullParser parser = null;
        FileInputStream fIn = null;
        try {
            fc = XmlPullParserFactory.newInstance();
            parser = fc.newPullParser();
            String sdcard = Environment.
                    getExternalStorageDirectory().getAbsolutePath();
            String xmlfile = sdcard + "/employee.xml";
            fIn = new FileInputStream(xmlfile);

            parser.setInput(fIn, "UTF-8");

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            Toast.makeText(this, "Doc file", Toast.LENGTH_LONG).show();
        }
        int eventType = -1;
        String nodeName;
        String datashow = "";
        while (eventType != XmlPullParser.END_DOCUMENT) {
            try {
                eventType = parser.nextTag();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
