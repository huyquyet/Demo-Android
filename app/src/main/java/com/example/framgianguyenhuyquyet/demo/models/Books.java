package com.example.framgianguyenhuyquyet.demo.models;

import java.io.Serializable;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 09/03/2016.
 */
public class Books implements Serializable {
    private String id;
    private String title;
    private String date;
    private String authorid;

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Books() {
    }

    public Books(String id, String title, String date, String authorid) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.authorid = authorid;
    }
}
