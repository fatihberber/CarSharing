package com.example.fatihberber.myapplication.Process;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.fatihberber.myapplication.Models.Arac;

public class Session {

    private SharedPreferences preferences;
    public Session(Context context){
        preferences= PreferenceManager.getDefaultSharedPreferences(context);
    }
    public void setUserId(String userId){
        preferences.edit().putString("usersId",userId).commit();
    }
    public String getUserId(){
        String userId=preferences.getString("usersId"," ");
        return userId;
    }
    public void setUsersId(Integer userId){
        preferences.edit().putInt("userId",userId).commit();
    }
    public Integer getUsersId(){
        Integer userId=preferences.getInt("userId",1);
        return userId;
    }
    public void setAracId(Integer aracId){
        preferences.edit().putInt("AracId",aracId).commit();
    }
    public Integer getAracId(){
        Integer aracId=preferences.getInt("AracId",1);
        return aracId;
    }
    public void setAracId2(Integer aracId){
        preferences.edit().putInt("AracId2",aracId).commit();
    }
    public Integer getAracId2(){
        Integer aracId=preferences.getInt("AracId2",1);
        return aracId;
    }
    public void setknt(Integer aracId){
        preferences.edit().putInt("AracId2",aracId).commit();
    }
    public Integer getknt(){
        Integer aracId=preferences.getInt("AracId2",1);
        return aracId;
    }
}
