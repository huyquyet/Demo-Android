package com.example.framgianguyenhuyquyet.demo.models;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 01/03/2016.
 */
public abstract class Employee {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public abstract double tinhLuong();

    @Override
    public String toString(){
        return this.id +"-"+this.name;
    }
}
