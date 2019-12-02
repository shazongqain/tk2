package com.example.tiku2;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tiku2.bean.XqInfor;
import com.example.tiku2.bean.ZlInfor;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/11/1 0001.
 */

public class AppClient extends Application {
    private static RequestQueue requestQueue;
    private static SharedPreferences preferences;
    private List<ZlInfor> zlInfors;
    private List<XqInfor> xqInfors;
    @Override
    public void onCreate() {
        super.onCreate();

        requestQueue= Volley.newRequestQueue(this);
        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        zlInfors=new ArrayList<>();
        xqInfors=new ArrayList<>();
    }

    public static void setRequestQueue(JsonObjectRequest jsonObjectRequest){
        requestQueue.add(jsonObjectRequest);
    }

    public static void setUserName(String userName){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("UserName",userName);
        editor.commit();
    }

    public static String getUserName()
    {
        return preferences.getString("UserName","user1");
    }
    public static void setIp(String ip){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("ip",ip);
        editor.commit();
    }

    public static String getIp(){
        return preferences.getString("ip","10.172.176.94");
    }

    public void setYz(int yz){
        preferences.edit().putInt("yz",yz).apply();
    }
    public int getYz(){
        return preferences.getInt("yz",0);
    }

    public List<ZlInfor> getZlInfors() {
        return zlInfors;
    }

    public List<XqInfor> getXqInfors() {
        return xqInfors;
    }
}
