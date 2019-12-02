package com.example.tiku2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.tiku2.AppClient;
import com.example.tiku2.R;
import com.example.tiku2.bean.XqInfor;
import com.example.tiku2.bean.ZlInfor;
import com.example.tiku2.net.VolleyLo;
import com.example.tiku2.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class BClwzActivity extends AppCompatActivity {
    private EditText et_cp;
    private Button cx;
    private int[] ids;
    private List<ZlInfor> zlInfors;
    private List<XqInfor> xqInfors;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bclwz);
        initView();
        setListener();
    }

    private void setListener() {
        cx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(et_cp.getText().toString())){
                    Toast.makeText(BClwzActivity.this, "请输入车牌号", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (int i=0;i<zlInfors.size();i++){
                    ZlInfor infor=zlInfors.get(i);
                    if (infor.getCp().equals(et_cp.getText().toString())){
                        ZlInfor infor1=zlInfors.get(0);
                        zlInfors.set(0,infor);
                        zlInfors.set(i,infor1);
                        startActivity(new Intent(BClwzActivity.this, BCxjgActivity.class));
                        finish();
                        return;
                    }
                }
                getWZ(et_cp.getText().toString());
            }
        });

    }

    private void getWZ(final String cp) {
        Log.d("yyyyyyyyyyyyyy", "getWZ: ");
        VolleyTo volleyTo=new VolleyTo();
        volleyTo.setUrl("get_peccancy_plate").setJsonObject("plate", "鲁"+cp).setVolleyLo(new VolleyLo() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray=jsonObject.getJSONArray("id");
                    ids=new int[jsonArray.length()];
                    for (int i=0;i<jsonArray.length();i++){
                        ids[i]=jsonArray.getInt(i);
                    }
                    getInfor();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("aaaaaaaaaaaa", e.toString());
                    Toast.makeText(BClwzActivity.this, "没有查询到"+cp+"车的违章数据", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("aaaaaaaaaaaa", volleyError.toString());
            }
        }).start();
    }

    private void getInfor() {
        VolleyTo volleyTo=new VolleyTo();
        volleyTo.setUrl("get_all_car_peccancy").setVolleyLo(new VolleyLo() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    int wcl=0,kf=0,fk=0;
                    JSONArray jsonArray=jsonObject.getJSONArray("ROWS_DETAIL");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        for (int x=0;x<ids.length;x++){
                            if (jsonObject1.getInt("id")==ids[x]){
                                wcl++;
                                kf+=jsonObject1.getInt("deduct");
                                fk+=jsonObject1.getInt("fine");
                                xqInfors.add(new XqInfor(et_cp.getText().toString(),
                                        jsonObject1.getString("time"),
                                        jsonObject1.getString("road"),
                                        jsonObject1.getString("message"),
                                        jsonObject1.getInt("deduct"),
                                        jsonObject1.getInt("fine")));
                            }
                        }
                    }
                    zlInfors.add(0,new ZlInfor(et_cp.getText().toString(),wcl,kf,fk));
                    startActivity(new Intent(BClwzActivity.this, BCxjgActivity.class));
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("ttttttttttt", e.toString());
                }
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("ttttttttttt", volleyError.toString());
            }
        }).start();
    }



    private void initView() {
        et_cp=findViewById(R.id.et_cp);
        cx=findViewById(R.id.cx);
        AppClient appClient= (AppClient) getApplication();
        zlInfors=appClient.getZlInfors();
        xqInfors=appClient.getXqInfors();
        TextView title=findViewById(R.id.title);
        title.setText("车辆违章");
        ImageView change=findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
