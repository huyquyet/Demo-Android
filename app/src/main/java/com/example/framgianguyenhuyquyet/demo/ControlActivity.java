package com.example.framgianguyenhuyquyet.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ControlActivity extends AppCompatActivity {
    Button btnTT, btnTiep, btnTK;
    ImageButton btnThoat;
    EditText editTen, editSl, editTongKH, editTongKhVip, editTongTT;
    TextView txtTT;
    CheckBox chkVip;
    DanhSachKhachHang danhsach = new DanhSachKhachHang();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getControls();
        addEvents();
    }

    private void getControls() {
        btnTT = (Button) findViewById(R.id.btntinhtt);
        btnTiep = (Button) findViewById(R.id.btntiep);
        btnTK = (Button) findViewById(R.id.btnthongke);
        btnThoat = (ImageButton) findViewById(R.id.imgbtnthoat);
        editTen = (EditText) findViewById(R.id.editTenKH);
        editSl = (EditText) findViewById(R.id.editsoluongsach);
        editTongKH = (EditText) findViewById(R.id.edittongsokh);
        editTongKhVip = (EditText) findViewById(R.id.edittongsokhlavip);
        editTongTT = (EditText) findViewById(R.id.edittongdt);
        txtTT = (TextView) findViewById(R.id.txtthanhtien);
        chkVip = (CheckBox) findViewById(R.id.chklavip);
    }

    private void addEvents() {
        btnTT.setOnClickListener(new ProcessMyEvent());
        btnTiep.setOnClickListener(new ProcessMyEvent());
        btnTK.setOnClickListener(new ProcessMyEvent());
        btnThoat.setOnClickListener(new ProcessMyEvent());
    }

    private void doTinhTien() {
        KhachHang kh = new KhachHang();
        kh.setTenKH((editTen.getText().toString()));
        kh.setSlmua(Integer.parseInt(editSl.getText().toString()));
        kh.setIsVip(chkVip.isChecked());
        txtTT.setText(String.valueOf(kh.tinhThanhTien()));
        danhsach.addKhachHang(kh);
    }

    private void doTiep() {
        editTen.setText("");
        editSl.setText("");
        txtTT.setText("");
        editTen.requestFocus();
    }

    private void doThongKe() {
        editTongKH.setText(String.valueOf(danhsach.tongKhachHang()));
        editTongKhVip.setText(String.valueOf(danhsach.tongKhachHangVip()));
        editTongTT.setText(String.valueOf(danhsach.tongDoanhThu()));
    }

    private void doThoat() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thoat ?");
        builder.setMessage(" Thoat chuong trinh nay ha ? ");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.create().show();
    }

    private class ProcessMyEvent implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btntinhtt:
                    doTinhTien();
                    break;
                case R.id.btntiep:
                    doTiep();
                    break;
                case R.id.btnthongke:
                    doThongKe();
                    break;
                case R.id.imgbtnthoat:
                    doThoat();
                    break;
            }
        }
    }
}
