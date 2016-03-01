package com.example.framgianguyenhuyquyet.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.example.framgianguyenhuyquyet.demo.models.Employee;

import java.util.ArrayList;

public class CustomLayoutListViewActivity extends AppCompatActivity {
    ArrayList<Employee> arrEmployee = new ArrayList<Employee>();
    MyArrayAdapter adapter;
    ListView lvNhanVien;
    Button btn_nhap, btn_back;
    ImageButton btn_remover;
    EditText edit_maNV, edit_tenNV;
    RadioGroup radioGroup_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_layout_list_view);
        btn_nhap = (Button) findViewById(R.id.btn_nhap);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_remover = (ImageButton) findViewById(R.id.btndelete);
        edit_maNV = (EditText) findViewById(R.id.editMaNV);
        edit_tenNV = (EditText) findViewById(R.id.editTenNV);
        radioGroup_gender = (RadioGroup) findViewById(R.id.radioGroup1);

        lvNhanVien = (ListView) findViewById(R.id.lvnhanvien);
        arrEmployee = new ArrayList<Employee>();
        /**
         * Khoi tao adapter
         */
        adapter = new MyArrayAdapter(this, R.layout.custom_layout, arrEmployee);
        lvNhanVien.setAdapter(adapter);
        btn_nhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyNhap();
            }
        });
        btn_remover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyXoa();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void xulyNhap() {
        String ma_NV = (edit_maNV.getText()).toString();
        String ten_NV = (edit_tenNV.getText()).toString();
        boolean gender = true; // Nam
        if (radioGroup_gender.getCheckedRadioButtonId() == R.id.radNu)
            gender = false;
        Employee employee = new Employee(ma_NV, ten_NV, gender);
        arrEmployee.add(employee);
        adapter.notifyDataSetChanged();
        edit_maNV.setText("");
        edit_tenNV.setText("");
        edit_maNV.requestFocus();

    }

    private void xulyXoa() {

        for (int i = lvNhanVien.getChildCount() - 1; i >= 0; i--) {
            View view = lvNhanVien.getChildAt(i);
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox_chon);
            if (checkBox.isChecked())
                arrEmployee.remove(i);
        }
        adapter.notifyDataSetChanged();
    }
}
