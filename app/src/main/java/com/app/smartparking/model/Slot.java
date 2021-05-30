package com.app.smartparking.model;

import java.io.Serializable;

public class Slot implements Serializable {
    private String name;
    private String area;
    private boolean available;

    public Slot() {
    }

    //create
    public Slot(String name, String area, boolean available) {
        this.name = name;
        this.area = area;
        this.available = available;
    }

    //update slot
    public Slot(String name, String area) {
        this.name = name;
        this.area = area;
        this.available = false;
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}