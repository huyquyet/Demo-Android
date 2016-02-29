package com.example.framgianguyenhuyquyet.demo;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 29/02/2016.
 */
public class KhachHang {
    private String tenKH;
    private int slmua;
    private boolean isVip;
    public static final int gia = 20000;

    public KhachHang() {
    }

    public KhachHang(String TenKH, int SlMua, boolean IsVip) {
        this.tenKH = TenKH;
        this.isVip = IsVip;
        this.slmua = SlMua;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setIsVip(boolean isVip) {
        this.isVip = isVip;
    }

    public int getSlmua() {
        return slmua;
    }

    public void setSlmua(int slmua) {
        this.slmua = slmua;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public double tinhThanhTien() {
        return (!isVip ? slmua * gia : slmua * gia * 0.9);
    }
}
