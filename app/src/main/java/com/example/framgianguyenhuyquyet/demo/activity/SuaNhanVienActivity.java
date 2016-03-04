package com.example.framgianguyenhuyquyet.demo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.framgianguyenhuyquyet.demo.R;
import com.example.framgianguyenhuyquyet.demo.model.NhanVien;

public class SuaNhanVienActivity extends AppCompatActivity {
    private Button btnXoaTrang, btnLuuNhanVien;
    private EditText edit_MaNV, edit_tenNV;
    private RadioButton radNam;
    NhanVien nhanVien = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhan_vien);
        getControl();
        getDataDefault();
        clickEvent();
    }

    private void getControl() {
        btnXoaTrang = (Button) findViewById(R.id.btnxoatrang);
        btnLuuNhanVien = (Button) findViewById(R.id.btnluunv);
        edit_MaNV = (EditText) findViewById(R.id.editMaNV);
        edit_tenNV = (EditText) findViewById(R.id.editTenNV);
        radNam = (RadioButton) findViewById(R.id.radNam);
        edit_MaNV.setEnabled(false);
        edit_tenNV.requestFocus();
    }

    private void getDataDefault() {
        /**
         * Intent intent = getIntent();
         * Lay ve Intent hien tai  cua Activity
         * Intent nay la Intent start Activity nay tuc Intent khai bao trong Activity de khoi tao Activity nay
         */
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("DATA");
        nhanVien = (NhanVien) bundle.getSerializable("NHANVIEN");
        edit_MaNV.setText(nhanVien.getMa());
        edit_tenNV.setText(nhanVien.getTen());
        radNam.setChecked(true);
        if (nhanVien.isGioitinh())
            radNam.setChecked(false);

        System.out.print(nhanVien.getMa());
        System.out.print(nhanVien.getTen());
        System.out.print(nhanVien.getChucVu());
        System.out.print(nhanVien.isGioitinh());
//        System.out.print(nhanVien.getMa());
    }

    private void clickEvent() {
        btnXoaTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_tenNV.setText("");
                edit_tenNV.requestFocus();
            }
        });
        btnLuuNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Lay ve Intent hien tai cua Activity
                 * ( Intent hien tai cua Activity la Intent dc truyen tu Activity goi Activity hien tai)
                 * Ko dc dung Intent intent = new Intent();
                 * Neu new Intent moi thi  khi goi ham setResult() no se ko biet tra ve Activity nao
                 */
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                nhanVien.setMa(edit_MaNV.getText().toString());
                nhanVien.setTen(edit_tenNV.getText().toString());
                nhanVien.setGioitinh(!radNam.isChecked());
                bundle.putSerializable("NHANVIEN", nhanVien);
                intent.putExtra("DATA", bundle);
                setResult(MainActivity.SUA_NHAN_VIEN_THANHCONG, intent);
                finish();
            }
        });
    }

}
