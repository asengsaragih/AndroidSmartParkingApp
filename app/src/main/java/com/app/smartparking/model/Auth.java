package com.app.smartparking.model;

import com.app.smartparking.base.Constant;

import java.io.Serializable;

public class Auth implements Serializable {
    private String fullname;
    private String email;
    private int role;

    public Auth() {
        //firebase require empty construct
    }

    public Auth(String fullname, String email) {
        this.fullname = fullname;
        this.email = email;
        this.role = Constant.ROLE_CLIENT;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public int getRole() {
        return role;
    }
}
