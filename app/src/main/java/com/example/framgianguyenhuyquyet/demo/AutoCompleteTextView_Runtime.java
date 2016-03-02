package com.example.framgianguyenhuyquyet.demo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.framgianguyenhuyquyet.demo.models.Student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AutoCompleteTextView_Runtime extends AppCompatActivity {
    ListView lv_sinhvien;
    EditText editMa, editTen, editNamsinh;
    RadioGroup radioGroup_gender;
    Button btnNamsinh, btnNhapsv ;
    ImageButton btn_delete;

    MyArrayAdapter adapterSinhVien;
    ArrayList<Student> arrSinhVien = new ArrayList<Student>();

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterAuto;
    ArrayList<String> arrAuto = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_text_view__runtime);
        getControl();
        clickEventButton();
    }

    public void getControl() {
        editMa = (EditText) findViewById(R.id.editMa);
        editTen = (EditText) findViewById(R.id.editTen);
        editNamsinh = (EditText) findViewById(R.id.editNgaySinh);
        radioGroup_gender = (RadioGroup) findViewById(R.id.radioGroup_gender);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteNS);
        btnNamsinh = (Button) findViewById(R.id.btnNgaySinh);
        btnNhapsv = (Button) findViewById(R.id.btnNhap);
        btn_delete = (ImageButton) findViewById(R.id.btn_delete);
        lv_sinhvien = (ListView) findViewById(R.id.lvsinhvien);
        adapterSinhVien = new MyArrayAdapter(this, R.layout.custom_listview_layout, arrSinhVien);

        lv_sinhvien.setAdapter(adapterSinhVien);
    }

    public void clickEventButton() {
        btnNamsinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        editNamsinh.setText(String.valueOf(dayOfMonth + "/" + monthOfYear + "/" + year));
                    }

                };
                DatePickerDialog datePicker = new DatePickerDialog(AutoCompleteTextView_Runtime.this, callBack, 1990, 12, 17);
                datePicker.setTitle("Birthday");
                datePicker.show();
            }
        });

        btnNhapsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maSV = editMa.getText().toString();
                String tenSV = editTen.getText().toString();
                String ngaySinh = editNamsinh.getText().toString();
                String autoText = autoCompleteTextView.getText().toString();
                boolean gender = false; //Nam
                if (radioGroup_gender.getCheckedRadioButtonId() == R.id.radio_female)
                    gender = true; //Nu

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

                try {
                    Student student = new Student(maSV, tenSV, gender, simpleDateFormat.parse(ngaySinh), autoText);
                    arrSinhVien.add(student);
                    adapterSinhVien.notifyDataSetChanged();
                    setAutoComplete(autoText);
                } catch (Exception e) {
                    Toast.makeText(AutoCompleteTextView_Runtime.this, "Loi roi cha noi " + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AutoCompleteTextView_Runtime.this);
                builder.setTitle("Xoa nhan vien");
                builder.setMessage("May lai muon xoa nua ah");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = lv_sinhvien.getChildCount() - 1; i >= 0; i--) {
                            View view = lv_sinhvien.getChildAt(i);
                            CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox_chonSV);
                            if (checkBox.isChecked())
                                arrSinhVien.remove(i);
                        }
                        adapterSinhVien.notifyDataSetChanged();
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
        });

    }

    private void setAutoComplete(String data) {
        for (int i = 0; i < arrAuto.size(); i++) {
            String x = arrAuto.get(i);
            if (x.trim().equalsIgnoreCase(data.trim()))
                return;
        }
        arrAuto.add(data);
        //Đưa vào ADapter
        adapterAuto = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrAuto);
        //đưa Adapter vào AutoComplete
        autoCompleteTextView.setAdapter(adapterAuto);
    }

}
