package com.example.framgianguyenhuyquyet.demo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

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

        lvNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View v = lvNhanVien.getChildAt(position);
                CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkbox_chon);
                checkBox.setChecked(true);
            }
        });

    }

    private void xulyNhap() {
        String ma_NV = (edit_maNV.getText()).toString();
        String ten_NV = (edit_tenNV.getText()).toString();
        if (!ma_NV.equals("") && !ten_NV.equals("")) {
            boolean gender = true; // Nam
            if (radioGroup_gender.getCheckedRadioButtonId() == R.id.radNu)
                gender = false;
            Employee employee = new Employee(ma_NV, ten_NV, gender);
            arrEmployee.add(employee);
            adapter.notifyDataSetChanged();
            edit_maNV.setText("");
            edit_tenNV.setText("");
            edit_maNV.requestFocus();
        } else {
            Toast.makeText(this, "Nhap day du thong tin", Toast.LENGTH_LONG).show();
        }
    }

    private void xulyXoa() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CustomLayoutListViewActivity.this);
        builder.setTitle("Xoa nhan vien");
        builder.setMessage("May lai muon xoa nua ah");
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = lvNhanVien.getChildCount() - 1; i >= 0; i--) {
                    View view = lvNhanVien.getChildAt(i);
                    CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox_chon);
                    if (checkBox.isChecked())
                        arrEmployee.remove(i);
                }
                adapter.notifyDataSetChanged();
            }
        });
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
}
