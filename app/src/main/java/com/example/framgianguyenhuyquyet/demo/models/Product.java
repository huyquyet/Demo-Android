package com.example.framgianguyenhuyquyet.demo.models;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 02/03/2016.
 */
public class Product extends SmartPhone {
    private Category DMuc;

    public Category getDMuc() {
        return DMuc;
    }

    public void setDMuc(Category DMuc) {
        this.DMuc = DMuc;
    }

    public Product(String ma, String name, Category dmuc) {
        super(ma, name);
        this.DMuc = dmuc;
    }

    public Product(String ma, String name) {
        super(ma, name);
    }

    public Product() {
        super();
    }
}
