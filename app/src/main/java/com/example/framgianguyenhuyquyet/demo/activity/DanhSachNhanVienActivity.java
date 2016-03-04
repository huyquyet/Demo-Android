package com.example.framgianguyenhuyquyet.demo.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.framgianguyenhuyquyet.demo.R;
import com.example.framgianguyenhuyquyet.demo.adapter.NhanVienAdapter;
import com.example.framgianguyenhuyquyet.demo.model.NhanVien;
import com.example.framgianguyenhuyquyet.demo.model.PhongBan;

import java.util.ArrayList;

public class DanhSachNhanVienActivity extends AppCompatActivity {
    TextView txtmsg;
    ImageButton btnback;
    ListView lvNhanvien;
    ArrayList<NhanVien> arrNhanvien = null;
    //Nhân viên Adapter để hiển thị thông tin
    //và chi tiết : chức vụ, giới tính
    NhanVienAdapter adapter = null;
    PhongBan pb = null;
    private NhanVien nvSelected = null;
    private int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_nhan_vien);
        getControl();
        getDataFromMain();
        clickEvent();
        registerForContextMenu(lvNhanvien);
    }

    private void getControl() {
        txtmsg = (TextView) findViewById(R.id.txtmsg);
        btnback = (ImageButton) findViewById(R.id.btnback);
        lvNhanvien = (ListView) findViewById(R.id.lvnhanvien);
    }

    private void getDataFromMain() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("DATA");
        pb = (PhongBan) bundle.getSerializable("PHONGBAN");
        arrNhanvien = pb.getListNhanVien();
        adapter = new NhanVienAdapter(this, R.layout.custom_listview, arrNhanvien);
        lvNhanvien.setAdapter(adapter);
        txtmsg.setText("DS nhân viên [" + pb.getTen() + "]");
    }

    private void clickEvent() {
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBackMain();
            }
        });

        // set su kien khi giu item trong list view
        lvNhanvien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int arg, long id) {
                nvSelected = arrNhanvien.get(arg);
                position = arg;
                return false;
            }
        });
    }

    /**
     * Set su kien cho list view
     */
    // set contextMenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextmenu_nhanvien, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnusuanv:
                doSuaNhanVien();
                break;
            case R.id.mnuchuyenpb:
                doChuyenPhongBan();
                break;
            case R.id.mnuxoanv:
                doXoaNhanVien();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == MainActivity.SUA_NHAN_VIEN_THANHCONG) {
            Bundle bundle = data.getBundleExtra("DATA");
            NhanVien nhanVien = (NhanVien) bundle.getSerializable("NHANVIEN");
            arrNhanvien.set(position, nhanVien);
            adapter.notifyDataSetChanged();
        } else if (resultCode == MainActivity.CHUYENPHONG_THANHCONG) {
            arrNhanvien.remove(nvSelected);
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * Mo Activity sua nhan vien
     */
    public void doSuaNhanVien() {
        Intent intent = new Intent(this, SuaNhanVienActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("NHANVIEN", nvSelected);
        intent.putExtra("DATA", bundle);
        startActivityForResult(intent, MainActivity.MO_ACTIVITY_SUA_NHAN_VIEN);
    }

    /**
     * Mo Activity chuyen phong ban cho nhan vien
     */
    public void doChuyenPhongBan() {
        Intent intent = new Intent(this, ChuyenPhongBanActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("NHANVIEN", nvSelected);
        intent.putExtra("DATA", bundle);
        startActivityForResult(intent, MainActivity.MO_ACTIVITY_CHUYENPHONG);
    }

    /**
     * Xoa nhan vien duoc chon
     */
    public void doXoaNhanVien() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setMessage("");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                arrNhanvien.remove(nvSelected);
                adapter.notifyDataSetChanged();
            }
        });
        builder.create().show();
    }

    public void doBackMain() {
        /**
         * Lay ve Intent hien tai cua Activity
         * ( Intent hien tai cua Activity la Intent dc truyen tu Activity goi Activity hien tai)
         * Ko dc dung Intent intent = new Intent();
         * Neu new Intent moi thi  khi goi ham setResult() no se ko biet tra ve Activity nao
         */
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("PHONGBAN", pb);
        intent.putExtra("DATA", bundle);
        setResult(MainActivity.CAPNHAT_DS_NHAN_VIEN_THANHCONG, intent);
    }
}
