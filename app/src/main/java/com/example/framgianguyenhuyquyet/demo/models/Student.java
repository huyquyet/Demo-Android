package com.example.framgianguyenhuyquyet.demo.models;

import java.util.Date;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 02/03/2016.
 */
public class Student {

    private String id;
    private String name;
    private boolean gender;
    private Date brithday;
    private String placeOfBirth;

    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
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

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Student(String id, String name, boolean gender, Date brithday, String placeOfBirth) {
        super();
        this.brithday = brithday;
        this.gender = gender;
        this.id = id;
        this.name = name;
        this.placeOfBirth = placeOfBirth;
    }

    public Student() {
        super();
    }
}
