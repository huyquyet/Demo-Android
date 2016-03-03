package com.example.framgianguyenhuyquyet.demo.models;

import java.io.Serializable;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 03/03/2016.
 */
public class data implements Serializable {
    private int a;
    private int b;
    private int c;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public data(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public data() {
    }
}
