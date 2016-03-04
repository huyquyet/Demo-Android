package com.example.framgianguyenhuyquyet.demo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.framgianguyenhuyquyet.demo.R;
import com.example.framgianguyenhuyquyet.demo.model.ChucVu;
import com.example.framgianguyenhuyquyet.demo.model.NhanVien;

public class ThemNhanVienActivity extends AppCompatActivity {
    private Button btnXoaTrang, btnLuuNhanVien;
    private EditText edit_MaNV, edit_tenNV;
    private RadioButton radNam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhan_vien);
        getControl();
        clickEvent();
    }

    private void getControl() {
        btnXoaTrang = (Button) findViewById(R.id.btnxoatrang);
        btnLuuNhanVien = (Button) findViewById(R.id.btnluunv);
        edit_MaNV = (EditText) findViewById(R.id.editMaNV);
        edit_tenNV = (EditText) findViewById(R.id.editTenNV);
        radNam = (RadioButton) findViewById(R.id.radNam);
    }

    private void clickEvent() {
        btnXoaTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_MaNV.setText("");
                edit_tenNV.setText("");
                edit_MaNV.requestFocus();
            }
        });

        btnLuuNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLuuNhanVien();
            }
        });
    }

    private void doLuuNhanVien() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMa(edit_MaNV.getText().toString());
        nhanVien.setTen(edit_tenNV.getText().toString());
        nhanVien.setChucVu(ChucVu.NhanVien);
        nhanVien.setGioitinh(!radNam.isChecked());

        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("NHANVIEN", nhanVien);
        intent.putExtra("DATA", bundle);
        setResult(MainActivity.THEM_NHAN_VIEN_THANHCONG, intent);
        finish();
    }
}
