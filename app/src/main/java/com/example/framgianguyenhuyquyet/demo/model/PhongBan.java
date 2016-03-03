package com.example.framgianguyenhuyquyet.demo.model;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.ArrayList;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 03/03/2016.
 */
public class PhongBan extends Infor implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<NhanVien> danhsach_nhanvien = new ArrayList<NhanVien>();

    public PhongBan(String ma, String ten) {
        super(ma, ten);
    }

    public PhongBan() {
        super();
    }

    public void themNV(NhanVien nhanVien) {
        int i = 0;
        //  Kiem tra nhan vien da ton tai hay chua
        for (; i < danhsach_nhanvien.size(); i++) {
            NhanVien nv_old = danhsach_nhanvien.get(i);
            //  Ton tai thi break vong lap
            if (nv_old.getMa().trim().equals(nhanVien.getMa().trim())) {

                break;
            }
        }
        //  Neu ton tai nhan vien
        //  Cap nhat lai thong tin nhan vien
        //  Chua ton tai thi them moi
        if (i < danhsach_nhanvien.size())
            danhsach_nhanvien.set(i, nhanVien);
        else
            danhsach_nhanvien.add(nhanVien);
    }

    public NhanVien get(int index) {
        return danhsach_nhanvien.get(index);
    }

    public int size() {
        return danhsach_nhanvien.size();
    }

    public NhanVien getTruongPhong() {
        for (int i = 0; i < danhsach_nhanvien.size(); i++) {
            NhanVien nhanVien = danhsach_nhanvien.get(i);
            if (nhanVien.getChucVu() == ChucVu.TruongPhong)
                return nhanVien;

        }
        return null;
    }

    public ArrayList<NhanVien> getPhoPhong() {
        ArrayList<NhanVien> danhsach_phophong = new ArrayList<NhanVien>();
        for (NhanVien nhanVien : danhsach_nhanvien) {
            if (nhanVien.getChucVu() == ChucVu.PhoPhong)
                danhsach_phophong.add(nhanVien);
        }
        return danhsach_phophong;
    }

    public ArrayList<NhanVien> getListNhanVien() {
        return this.danhsach_nhanvien;
    }

    @Override
    public String toString() {
        String str = super.toString();
        if (danhsach_nhanvien.size() == 0)
            str += "chua co nhan vien";
        else
            str += "co " + danhsach_nhanvien.size() + " nhan vien";
        return str;
    }
}
