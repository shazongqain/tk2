package com.example.tiku2;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;




import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/11/1 0001.
 */

public class AppClient extends Application {
    private static RequestQueue requestQueue;
    private static SharedPreferences preferences;
    @Override
    public void onCreate() {
        super.onCreate();

        requestQueue= Volley.newRequestQueue(this);
        preferences= PreferenceManager.getDefaultSharedPreferences(this);
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
        return preferences.getString("ip","192.168.43.218");
    }

    public void setYz(int yz){
        preferences.edit().putInt("yz",yz).apply();
    }
    public int getYz(){
        return preferences.getInt("yz",0);
    }
}
