package com.example.framgianguyenhuyquyet.demo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.framgianguyenhuyquyet.demo.models.Employee;
import com.example.framgianguyenhuyquyet.demo.models.EmployeeFullTime;
import com.example.framgianguyenhuyquyet.demo.models.EmployeePartTime;

import java.util.ArrayList;

public class ArrayList_ListView_Object extends Activity {
    Button btn_nhap, btn_back;
    EditText edit_maNV, edit_tenNV;
    RadioGroup radio_group;
    ListView lv_Employee;
    ArrayList<Employee> arrEmployee = new ArrayList<Employee>();
    ArrayAdapter<Employee> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list__list_view__object);
        getControl();
        getEvents();
    }

    private void getControl() {
        btn_nhap = (Button) findViewById(R.id.btnNhapNV);
        btn_back = (Button) findViewById(R.id.btn_back_object);
        edit_maNV = (EditText) findViewById(R.id.editMa);
        edit_tenNV = (EditText) findViewById(R.id.editTenNV);
        radio_group = (RadioGroup) findViewById(R.id.radioGroupButton1);
        lv_Employee = (ListView) findViewById(R.id.lvEmployee);
    }

    private void getEvents() {
        adapter = new ArrayAdapter<Employee>(this, android.R.layout.simple_list_item_1, arrEmployee);
        lv_Employee.setAdapter(adapter);

        btn_nhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputData();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void inputData() {
        int rad = radio_group.getCheckedRadioButtonId();
        String id = String.valueOf(edit_maNV.getText());
        String name = String.valueOf(edit_tenNV.getText());
        Employee employee;
        if (rad == R.id.radioNVFullTime) {
            employee = new EmployeeFullTime();
        } else {
            employee = new EmployeePartTime();
        }
        employee.setId(id);
        employee.setName(name);
        arrEmployee.add(employee);
        adapter.notifyDataSetChanged();
    }
}
