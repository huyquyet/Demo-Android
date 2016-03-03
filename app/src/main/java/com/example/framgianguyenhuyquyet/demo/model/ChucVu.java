package com.example.framgianguyenhuyquyet.demo.model;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 03/03/2016.
 */
public enum ChucVu {
    TruongPhong("Trưởng Phòng"),
    PhoPhong("Phó Phòng"),
    NhanVien("Nhân Viên");
    private String cv;

    ChucVu(String cv) {
        this.cv = cv;
    }

    public String getChucVu() {
        return this.cv;
    }
}
