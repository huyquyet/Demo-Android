package com.example.framgianguyenhuyquyet.demo.model;

import java.io.Serializable;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 03/03/2016.
 */
public class Infor implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ma;
    private String ten;

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Infor(String ma, String ten) {
        super();
        this.ma = ma;
        this.ten = ten;
    }

    public Infor() {
        super();
    }

    @Override
    public String toString() {
        return this.ma + " - " + this.ten;
    }
}
