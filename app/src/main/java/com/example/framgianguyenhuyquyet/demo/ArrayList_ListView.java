package com.example.framgianguyenhuyquyet.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ArrayList_ListView extends AppCompatActivity {
    Button btnNhap;
    ListView lv;
    TextView txtSelection;
    EditText editNhap;
    ArrayList<String> danhsach = null;
    ArrayAdapter<String> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list__list_view);

        lv = (ListView) findViewById(R.id.lvPerson);
        txtSelection = (TextView) findViewById(R.id.txtselection);
        editNhap = (EditText) findViewById(R.id.txtTen);
        danhsach = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, danhsach);
        lv.setAdapter(adapter);
        btnNhap = (Button) findViewById(R.id.btnNhap);

        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = String.valueOf(editNhap.getText());
                if (!text.equals("")) {
                    danhsach.add(String.valueOf(editNhap.getText()));
                    adapter.notifyDataSetChanged();
                    editNhap.setText("");
                } else {
                    Toast.makeText(ArrayList_ListView.this, "Nhap Text Di May :v ", Toast.LENGTH_LONG).show();
                }
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                txtSelection.setText(String.valueOf("position : " + position + "    value : " + danhsach.get(position)));
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            int Position;

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Position = position;
                AlertDialog.Builder confirm = new AlertDialog.Builder(ArrayList_ListView.this);
                confirm.setTitle("Delete data");
                confirm.setMessage("Ban co mua xoa");
                confirm.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        danhsach.remove(Position);
                        adapter.notifyDataSetChanged();
                    }
                });
                confirm.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                confirm.create().show();
                return false;
            }
        });
    }
}
