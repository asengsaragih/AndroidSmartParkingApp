package com.app.smartparking.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Setting implements Serializable {
    private String title;
    private String desc;
    private Drawable icon;

    public Setting(String title, String desc, Drawable icon) {
        this.title = title;
        this.desc = desc;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public Drawable getIcon() {
        return icon;
    }
}
