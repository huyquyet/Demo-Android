package com.example.framgianguyenhuyquyet.demo.models;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 01/03/2016.
 */
public  class EmployeePartTime extends Employee {

    @Override
    public double tinhLuong() {
        return 50;
    }

    @Override
    public String toString() {
        return super.toString() + " --> partTime";
    }
}
