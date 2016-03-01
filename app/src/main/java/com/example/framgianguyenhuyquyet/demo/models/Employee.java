package com.example.framgianguyenhuyquyet.demo.models;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 01/03/2016.
 */
public class Employee {
    private String id;
    private String name;
    private boolean gender;

    public Employee() {
    }

    public Employee(String id, String name, boolean gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

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

    @Override
    public String toString() {
        return this.id + "-" + this.name;
    }
}
