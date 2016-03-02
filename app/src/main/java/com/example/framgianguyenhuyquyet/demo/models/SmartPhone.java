package com.example.framgianguyenhuyquyet.demo.models;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 02/03/2016.
 */
public class SmartPhone {

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

    public SmartPhone() {
        super();
    }

    public SmartPhone(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return this.id + " - " + this.name;
    }
}
