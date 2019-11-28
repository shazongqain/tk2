package com.example.tiku2.net;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.tiku2.AppClient;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2019/10/26 0026.
 */

public class VolleyTo extends Thread {
    private String Url = "http://"+ AppClient.getIp()+":8989/traffic/";
    private JSONObject jsonObject = new JSONObject();
    private int Time;
    private boolean isLoop;
    private ProgressDialog dialog;
    private VolleyLo volleyLo;

    public VolleyTo setUrl(String url) {
        try {
            jsonObject.put("UserName",AppClient.getUserName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Url += url;
        return this;
    }

    public VolleyTo setJsonObject(String k, Object v) {
        try {
            jsonObject.put(k,v);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public VolleyTo setTime(int time) {
        Time = time;
        return this;
    }

    public VolleyTo setLoop(boolean loop) {
        isLoop = loop;
        return this;
    }

    public VolleyTo setDialog(Context context) {
        dialog=new ProgressDialog(context);
        dialog.setTitle("提示");
        dialog.setMessage("网络请求中。。。。");
        dialog.show();
        return this;
    }

    public VolleyTo setVolleyLo(VolleyLo volleyLo) {
        this.volleyLo = volleyLo;
        return this;
    }

    @Override
    public void run() {
        super.run();
        do {
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    volleyLo.onResponse(jsonObject);
                    if (dialog!=null&&dialog.isShowing()){
                        dialog.dismiss();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    volleyLo.onErrorResponse(volleyError);
                    if (dialog!=null&&dialog.isShowing()){
                        dialog.dismiss();
                    }
                }
            });
            AppClient.setRequestQueue(jsonObjectRequest);
            try {
                Thread.sleep(Time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (isLoop);
    }
}
