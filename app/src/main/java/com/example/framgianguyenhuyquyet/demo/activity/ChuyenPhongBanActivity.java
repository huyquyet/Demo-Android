package com.example.framgianguyenhuyquyet.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.framgianguyenhuyquyet.demo.R;
import com.example.framgianguyenhuyquyet.demo.model.NhanVien;
import com.example.framgianguyenhuyquyet.demo.model.PhongBan;

import java.util.ArrayList;

public class ChuyenPhongBanActivity extends AppCompatActivity {
    ListView lvPb;
    private static ArrayList<PhongBan> arrPhongBan = null;
    ArrayAdapter<PhongBan> adapter;
    ImageButton btnApply;
    NhanVien nv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuyen_phong_ban);
        getControl();
        clickEvent();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("DATA");
        nv = (NhanVien) bundle.getSerializable("NHANVIEN");
    }

    private void getControl() {
        lvPb = (ListView) findViewById(R.id.lvphongban);
        btnApply = (ImageButton) findViewById(R.id.imgapply);
        arrPhongBan = MainActivity.getListPhongBan();
        adapter = new ArrayAdapter<PhongBan>(this, android.R.layout.simple_list_item_1, arrPhongBan);
        lvPb.setAdapter(adapter);


    }

    private void clickEvent() {
        lvPb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            boolean somethingChecked = false;

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //hiển nhiên View arg1 là CheckedTextView
//                if (somethingChecked) {
//                    CheckedTextView cv = (CheckedTextView) arg1;
//                    cv.setChecked(false);
//
//                }
//                CheckedTextView cv = (CheckedTextView) arg1;
//                if (!cv.isChecked()) {
//                    cv.setChecked(true);
                    arrPhongBan.get(arg2).themNV(nv);
//                }
//                somethingChecked = true;
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                setResult(MainActivity.CHUYENPHONG_THANHCONG, intent);
                finish();
            }
        });
    }
}
