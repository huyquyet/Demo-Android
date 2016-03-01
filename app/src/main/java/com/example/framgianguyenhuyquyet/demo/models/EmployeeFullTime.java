package com.example.framgianguyenhuyquyet.demo.models;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 01/03/2016.
 */
public class EmployeeFullTime extends Employee {
    @Override
    public double tinhLuong() {
        return 100;
    }

    @Override
    public String toString() {
        return super.toString() + " --> fullTime";
    }
}
