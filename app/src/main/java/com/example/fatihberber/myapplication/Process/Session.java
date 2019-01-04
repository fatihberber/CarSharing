package com.example.fatihberber.myapplication.Process;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences preferences;
    public Session(Context context){
        preferences= PreferenceManager.getDefaultSharedPreferences(context);
    }
    public void setUserId(String userId){
        preferences.edit().putString("userId",userId).commit();
    }
    public String getUserId(){
        String userId=preferences.getString("userId"," ");
        return userId;
    }


}
