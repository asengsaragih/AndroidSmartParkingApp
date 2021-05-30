package com.app.smartparking.model;

import com.app.smartparking.base.Constant;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Order implements Serializable {

    private long dateTime;
    private String slotKey;
    private Slot slot;
    private String qrCode;
    private String uid;
    private int status;
    private long createdAt;
    private long updatedAt;

    //firebase required empty construct
    public Order() {
    }

    //for create order
    public Order(long dateTime, String slotKey, Slot slot, String uid) {
        this.dateTime = dateTime;
        this.slotKey = slotKey;
        this.slot = slot;
        this.qrCode = generateCode();
        this.uid = uid;
        this.status = Constant.STATUS_ORDER_ONGOING;
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }

    //for update order
    public Order(long dateTime, String slotKey, Slot slot, String qrCode, String uid, int status, long createdAt) {
        this.dateTime = dateTime;
        this.slotKey = slotKey;
        this.slot = slot;
        this.qrCode = qrCode;
        this.uid = uid;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = System.currentTimeMillis();
    }

    public long getDateTime() {
        return dateTime;
    }

    public String getSlotKey() {
        return slotKey;
    }

    public Slot getSlot() {
        return slot;
    }

    public String getUid() {
        return uid;
    }

    public int getStatus() {
        return status;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public String getQrCode() {
        return qrCode;
    }

    public static String generateCode() {
        //alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        //numeric
        String numeric = "0123456789";

        //merge alphabet + numeric
        String alphaNumeric = alphabet + numeric;

        //split merge variable into char
        char[] chars = alphaNumeric.toCharArray();

        //initialize random
        Random random = new SecureRandom();

        //tmplist for random char
        List<Character> tmpList = new ArrayList<>();

        //generate random char (50 digit)
        for (int i = 0; i < 8; i++) {
            char c = chars[random.nextInt(chars.length)];
            //insert into t
            tmpList.add(c);
        }

        //shuffle data in tmplist
        Collections.shuffle(tmpList);

        //new string builder
        StringBuilder builder = new StringBuilder();

        //insert tmplist into builder
        for (char c : tmpList) {
            builder.append(c);
        }

        //return value string builder to string
        return builder.toString();
    }
}
