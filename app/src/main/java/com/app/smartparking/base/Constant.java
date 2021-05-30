package com.app.smartparking.base;

public class Constant {
    public static final String SESSION_AUTH = "auth_session";

    public static final int ROLE_CLIENT = 1;
    public static final int ROLE_ADMIN = 2;

    public static final String FIREBASE_TABLE_USER = "Users";
    public static final String FIREBASE_TABLE_ORDER = "Order";
    public static final String FIREBASE_TABLE_SLOT = "Slot";

    public static final int STATUS_ORDER_ONGOING = 0;
    public static final int STATUS_ORDER_DONE = 1;
    public static final int STATUS_ORDER_PENDING = 2;

    public static final String DATE_FORMAT = "dd MMMM yyyy";
    public static final String TIME_FORMAT = "HH:mm";
    public static final String DATETIME_FORMAT = "dd MMMM yyyy | HH:mm";

    public static final String INTENT_ORDER = "order_intent";
    public static final String INTENT_SLOT = "slot_intent";
    public static final String INTENT_KEY = "key_intent";
}
