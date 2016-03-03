package com.example.framgianguyenhuyquyet.demo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.framgianguyenhuyquyet.demo.R;
import com.example.framgianguyenhuyquyet.demo.model.NhanVien;
import com.example.framgianguyenhuyquyet.demo.model.PhongBan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 03/03/2016.
 */
public class PhongBanAdapter extends ArrayAdapter<PhongBan> {
    Activity context;
    int resource;
    ArrayList<PhongBan> arrayList;

    public PhongBanAdapter(Activity context, int resource, ArrayList<PhongBan> arrayList) {
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
        TextView txt_PhongBan = (TextView) convertView.findViewById(R.id.txtShotInfo);
        TextView txt_MoTa_PhongBan = (TextView) convertView.findViewById(R.id.txtDetailInfor);

        PhongBan phongBan = arrayList.get(position);
        txt_PhongBan.setText(phongBan.toString());

        String mota = "";
        String truongPhong = "Trưởng Phòng: [Chưa có]";
        NhanVien nhanVien = phongBan.getTruongPhong();
        if (nhanVien != null) {
            truongPhong = "Trưởng Phòng: [" + nhanVien.getTen() + "]";
        }
        ArrayList<NhanVien> list_phoPhong = phongBan.getPhoPhong();
        String phoPhong = "Phó phòng: [Chưa có]";
        if (list_phoPhong.size() > 0) {
            phoPhong = "Phó phòng:|n";
            for (int i = 0; i < list_phoPhong.size(); i++) {
                phoPhong += (i + 1) + " . " + list_phoPhong.get(i).getTen() + "\n";
            }
        }
        mota = truongPhong + "\n" + phoPhong;
        txt_MoTa_PhongBan.setText(mota);
        return convertView;
    }
}
