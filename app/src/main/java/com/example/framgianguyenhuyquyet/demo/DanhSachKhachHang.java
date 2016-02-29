package com.example.framgianguyenhuyquyet.demo;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 29/02/2016.
 */
public class DanhSachKhachHang {

    ArrayList<KhachHang> listKhachHang = new ArrayList<KhachHang>();

    public void addKhachHang(KhachHang kh) {
        listKhachHang.add(kh);
    }

    public double tongDoanhThu() {
        double tien = 0.0;
        for (KhachHang kh : listKhachHang) {
            tien += kh.tinhThanhTien();
        }
        return tien;
    }

    public int tongKhachHangVip() {
        int vip = 0;
        for (KhachHang kh : listKhachHang) {
            if (kh.isVip())
                vip++;
        }
        return vip;
    }
    public  int tongKhachHang(){
        return listKhachHang.size();
    }
}
