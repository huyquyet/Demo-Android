package com.example.framgianguyenhuyquyet.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TabSelector_Activity extends AppCompatActivity {
    enum Operator {
        Cong, Tru, Nhan, Chia
    }

    Button btn_cong, btn_tru, btn_nhan, btn_chia;
    EditText edit_soA, edit_soB;
    TextView txt_ketqua;
    ListView lv_history;
    ArrayList<String> arrHistory = new ArrayList<String>();
    ArrayAdapter<String> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_selector_);
        loadTabs();
        loadControl();
        myEvent();
    }

    //    private void loadTab() {
//        final TabHost tab = (TabHost) findViewById(android.R.id.tabhost);
//        tab.setup();
//        TabHost.TabSpec spec;
//
//        // Create tabSpec, add tabSpec in TabHost
//        spec = tab.newTabSpec("tab1");
//        spec.setContent(R.id.tab1);
//        spec.setIndicator("Calculator");
//        tab.addTab(spec);
//
//        spec = tab.newTabSpec("tab2");
//        spec.setContent(R.id.tab2);
//        spec.setIndicator("History");
//        tab.addTab(spec);
//
//        //Set default tab, index start 0
//        tab.setCurrentTab(0);
//
//        tab.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
//            @Override
//            public void onTabChanged(String tabId) {
//                String s = "Tab tag =" + tabId + "; index =" +
//                        tab.getCurrentTab();
//                Toast.makeText(TabSelector_Activity.this, s, Toast.LENGTH_LONG).show();
//            }
//        });
//    }
    public void loadTabs() {
        //Lấy Tabhost id ra trước (cái này của built - in android
        final TabHost tab = (TabHost) findViewById(android.R.id.tabhost);
        //gọi lệnh setup
        tab.setup();
        TabHost.TabSpec spec;
        //Tạo tab1
        spec = tab.newTabSpec("t1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("1-Calculator");
        tab.addTab(spec);
        //Tạo tab2
        spec = tab.newTabSpec("t2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("2-History");
        tab.addTab(spec);
        //Thiết lập tab mặc định được chọn ban đầu là tab 0
        tab.setCurrentTab(0);
        //Ở đây Tôi để sự kiện này để các bạn tùy xử lý
        //Ví dụ tab1 chưa nhập thông tin xong mà lại qua tab 2 thì báo...
        tab.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String arg0) {
                String s = "Tab tag =" + arg0 + "; index =" +
                        tab.getCurrentTab();
                Toast.makeText(getApplicationContext(),
                        s, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadControl() {
        //  Button
        btn_cong = (Button) findViewById(R.id.btn_cong);
        btn_tru = (Button) findViewById(R.id.btn_tru);
        btn_nhan = (Button) findViewById(R.id.btn_nhan);
        btn_chia = (Button) findViewById(R.id.btn_chia);

        //  EditText
        edit_soA = (EditText) findViewById(R.id.edit_text_soA);
        edit_soB = (EditText) findViewById(R.id.edit_text_soB);

        //  TextView
        txt_ketqua = (TextView) findViewById(R.id.text_ketqua);

        // ListView

        lv_history = (ListView) findViewById(R.id.lv_history);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrHistory);
        lv_history.setAdapter(adapter);
    }

    private void myEvent() {
        btn_cong.setOnClickListener(new myClick());
        btn_tru.setOnClickListener(new myClick());
        btn_chia.setOnClickListener(new myClick());
        btn_nhan.setOnClickListener(new myClick());

    }

    private class myClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_cong:
                    tinhtoan(Operator.Cong);
                    break;
                case R.id.btn_tru:
                    tinhtoan(Operator.Tru);
                    break;
                case R.id.btn_nhan:
                    tinhtoan(Operator.Nhan);
                    break;
                case R.id.btn_chia:
                    tinhtoan(Operator.Chia);
                    break;
            }
        }
    }

    private void tinhtoan(Operator op) {
        String sa = edit_soA.getText().toString();
        String sb = edit_soB.getText().toString();
        int a = Integer.parseInt(sa);
        int b = Integer.parseInt(sb);
        String kq = "";
        switch (op) {
            case Cong:
                kq = a + " + " + b + " = " + (a + b);
                break;
            case Tru:
                kq = a + " - " + b + " = " + (a - b);
                break;
            case Chia:
                if (b != 0)
                    kq = a + " / " + b + " = " + (a * 1.0 / b);
                else
                    kq = "b phai khac 0";
                break;
            case Nhan:
                kq = a + " * " + b + " = " + (a * b);
                break;
            default:
                kq = "Loi tinh toan";
        }
        txt_ketqua.setText(kq);
        arrHistory.add(kq);
        adapter.notifyDataSetChanged();
    }
}
