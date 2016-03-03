package com.example.framgianguyenhuyquyet.demo.model;

import java.io.Serializable;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 03/03/2016.
 */
public class NhanVien extends Infor implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean gioitinh;
    private ChucVu chucVu;
    private PhongBan phongBan;

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public PhongBan getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
    }

    public NhanVien() {
        super();
    }

    public NhanVien(String ma, String ten, boolean gioitinh) {
        super(ma, ten);
        this.gioitinh = gioitinh;
    }

    public NhanVien(String ma, String ten, boolean gioitinh, ChucVu chucVu, PhongBan phongBan) {
        super(ma, ten);
        this.gioitinh = gioitinh;
        this.chucVu = chucVu;
        this.phongBan = phongBan;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
