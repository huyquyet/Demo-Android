package com.example.framgianguyenhuyquyet.demo.models;

import java.io.Serializable;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 08/03/2016.
 */
public class Author implements Serializable {
    private String id;
    private String firstname;
    private String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return this.firstname + " " + this.lastname;
    }
}
