package com.example.framgianguyenhuyquyet.demo.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.framgianguyenhuyquyet.demo.R;
import com.example.framgianguyenhuyquyet.demo.adapter.PhongBanAdapter;
import com.example.framgianguyenhuyquyet.demo.model.NhanVien;
import com.example.framgianguyenhuyquyet.demo.model.PhongBan;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Khai báo các Request + Result code để xử lý Intent for result
    public static final int MO_ACTIVITY_THEM_NHAN_VIEN = 1;
    public static final int MO_ACTIVITY_SUA_NHAN_VIEN = 2;
    public static final int THEM_NHAN_VIEN_THANHCONG = 3;
    public static final int SUA_NHAN_VIEN_THANHCONG = 4;
    public static final int XEM_DS_NHAN_VIEN = 5;
    public static final int CAPNHAT_DS_NHAN_VIEN_THANHCONG = 6;
    public static final int MO_ACTIVITY_THIET_LAP_TP_PP = 7;
    public static final int THIET_LAP_TP_PP_THANHCONG = 8;
    public static final int MO_ACTIVITY_CHUYENPHONG = 9;
    public static final int CHUYENPHONG_THANHCONG = 10;

    private Button btn_luuPhongBan;
    private EditText edit_maPB, edit_tenPB;
    private ListView lv_phongBan;
    private static ArrayList<PhongBan> arrPhongBan = new ArrayList<PhongBan>();

    private PhongBanAdapter adapterPB = null;
    private PhongBan pbSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        getControl();
        clickEvent();

    }


    private void getControl() {
        btn_luuPhongBan = (Button) findViewById(R.id.btnluupb);
        edit_maPB = (EditText) findViewById(R.id.editmapb);
        edit_tenPB = (EditText) findViewById(R.id.editTenpb);
        lv_phongBan = (ListView) findViewById(R.id.lvphongban);

        adapterPB = new PhongBanAdapter(this, R.layout.custom_listview, arrPhongBan);

        lv_phongBan.setAdapter(adapterPB);
        //  Dang ky ContextMenu cho ListView
        registerForContextMenu(lv_phongBan);
    }

    private void clickEvent() {
        btn_luuPhongBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLuuPhongBan();
            }
        });

        lv_phongBan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                pbSelected = arrPhongBan.get(position);
                return false;
            }
        });
    }

    // Chua xu ly trung ma phong ban, ten phong ban
    private void doLuuPhongBan() {
        String ma_PB = edit_maPB.getText().toString();
        String ten_PB = edit_tenPB.getText().toString();
        PhongBan phongBan = new PhongBan(ma_PB, ten_PB);
        arrPhongBan.add(phongBan);
        adapterPB.notifyDataSetChanged();
    }

    //  Dang ky su kien khi click vao list view
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextmenu_phongban, menu);
    }

    //  Lua chon su ly khi ContextMenu hien len
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuthemnv:
                doThemNhanVien();
                break;
            case R.id.mnudanhsachnv:
                doDanhSachNhanVien();
                break;
            case R.id.mnutruongphong:
                doThietLapLanhDao();
                break;
            case R.id.mnuxoapb:
                doXoaPhongBan();
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void doThemNhanVien() {
        Intent intent = new Intent(this, ThemNhanVienActivity.class);
        startActivityForResult(intent, MO_ACTIVITY_THEM_NHAN_VIEN);
    }

    //  Xu ly khi du lieu khi ket thuc Activity ThemNhanVienActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == THEM_NHAN_VIEN_THANHCONG) {
            Bundle bundle = data.getBundleExtra("DATA");
            NhanVien nhanVien = (NhanVien) bundle.getSerializable("NHANVIEN");
            pbSelected.themNV(nhanVien);
            adapterPB.notifyDataSetChanged();
        } else if (resultCode == THIET_LAP_TP_PP_THANHCONG || resultCode == CAPNHAT_DS_NHAN_VIEN_THANHCONG) {
            Bundle bundle = data.getBundleExtra("DATA");
            PhongBan phongBan = (PhongBan) bundle.getSerializable("PHONGBAN");

            //  Xoa danh sach nhan vien cu
            pbSelected.getListNhanVien().clear();
            //  Them lai danh sach nhan vien tu Activity them nhan vien tra ve
            try {
                pbSelected.getListNhanVien().addAll(phongBan.getListNhanVien());
            } catch (Exception e) {
                Toast.makeText(this, "Loi them nhan vien", Toast.LENGTH_LONG).show();
            }
            adapterPB.notifyDataSetChanged();
        }
    }

    //  Gui object phong ban hien tai de hien thi danh sach nhan vien
    public void doDanhSachNhanVien() {
        Intent intent = new Intent(this, DanhSachNhanVienActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("PHONGBAN", pbSelected);
        intent.putExtra("DATA", bundle);
        startActivityForResult(intent, XEM_DS_NHAN_VIEN);
    }

    public void doThietLapLanhDao() {
        Intent intent = new Intent(this, ThietLapTruongPhongActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("PHONGBAN", pbSelected);
        intent.putExtra("DATA", bundle);
        startActivityForResult(intent, MO_ACTIVITY_THIET_LAP_TP_PP);
    }

    public void doXoaPhongBan() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xoa Phong Ban");
        builder.setMessage("May lai xoa nua ah");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                arrPhongBan.remove(pbSelected);
                adapterPB.notifyDataSetChanged();
            }
        });
        builder.create().show();
    }

    public static ArrayList<PhongBan> getListPhongBan() {
        return arrPhongBan;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
