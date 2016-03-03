package com.example.framgianguyenhuyquyet.demo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framgianguyenhuyquyet.demo.R;
import com.example.framgianguyenhuyquyet.demo.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 03/03/2016.
 */
public class NhanVienAdapter extends ArrayAdapter<NhanVien> {
    Activity context;
    int resource;
    ArrayList<NhanVien> arrayList;

    public NhanVienAdapter(Activity context, int resource, ArrayList<NhanVien> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.resource = resource;
        this.arrayList = arrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(resource, null);

        // get control
        TextView txt_nhanVien = (TextView) convertView.findViewById(R.id.txtShotInfo);
        TextView txt_moTa_nhanVien = (TextView) convertView.findViewById(R.id.txtDetailInfor);
        ImageView img_view = (ImageView) convertView.findViewById(R.id.img_view);

        NhanVien nhanVien = arrayList.get(position);
        txt_nhanVien.setText(nhanVien.toString());

        String mota = "";
        String chucVu = "Chuc vu :" + nhanVien.getChucVu().getChucVu();
        String gioiTinh = "Gioi tinh :" + (nhanVien.isGioitinh() ? "Nu" : "Nam");
        img_view.setImageResource(R.drawable.girlicon);
        if (!nhanVien.isGioitinh())
            img_view.setImageResource(R.drawable.boyicon);
        mota = chucVu + "\n" + gioiTinh;
        txt_moTa_nhanVien.setText(mota);
        return convertView;
    }
}
