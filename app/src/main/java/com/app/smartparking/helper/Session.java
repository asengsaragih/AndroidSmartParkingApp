package com.app.smartparking.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.smartparking.R;
import com.app.smartparking.base.Constant;
import com.app.smartparking.model.Auth;
import com.google.gson.Gson;

public class Session {
    private final Gson gson;
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;

    public Session(Context context) {
        this.gson = new Gson();
        this.preferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        this.editor = preferences.edit();
    }

    //set auth
    public void setAuth(Auth auth) {
        String authString = gson.toJson(auth);

        //put in shared
        editor.putString(Constant.SESSION_AUTH, authString);
        editor.commit();
    }

    //get auth
    public Auth getAuth() {
        String authString = preferences.getString(Constant.SESSION_AUTH, null);

        //get auth
        return gson.fromJson(authString, Auth.class);
    }
}
