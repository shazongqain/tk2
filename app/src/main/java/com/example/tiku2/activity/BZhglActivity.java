package com.example.tiku2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.tiku2.AppClient;
import com.example.tiku2.R;
import com.example.tiku2.adapter.ZhglAdapter;
import com.example.tiku2.bean.ZhglInfor;
import com.example.tiku2.dialog.ZhczDialog;
import com.example.tiku2.net.VolleyLo;
import com.example.tiku2.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BZhglActivity extends AppCompatActivity {
private ListView lv;
private List<ZhglInfor> list;
private ZhglAdapter adapter;
private TextView pl,record;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bzhgl);
        initView();
        getData();
        setListener();
    }

    private void setListener() {
        adapter.setData(new ZhglAdapter.SetData() {
            @Override
            public void setdata(int lx, int position, boolean check) {
                ZhglInfor infor=list.get(position);
                if (lx==1){
                    infor.setCheck(check);
                }else {
                    ZhczDialog dialog=new ZhczDialog(BZhglActivity.this,infor.getCp(),infor.getYue()+"");
                    dialog.show(getSupportFragmentManager(),"");
                    dialog.setData(new ZhczDialog.CzData() {
                        @Override
                        public void czdata() {
                            getData();
                        }
                    });
                }
            }
        });

        pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cp="";
                String yue="";
                for (int i=0;i<list.size();i++){
                    ZhglInfor infor=list.get(i);
                    if (infor.isCheck()){
                        if (cp.equals("")){
                            cp=infor.getCp();
                            yue=infor.getYue()+"";
                        }else {
                            cp+=" "+infor.getCp();
                            yue+=" "+infor.getYue();
                        }
                    }
                }
                if (cp.equals("")){
                    Toast.makeText(BZhglActivity.this, "请选择充值的账户", Toast.LENGTH_SHORT).show();
                    return;
                }

                ZhczDialog dialog=new ZhczDialog(BZhglActivity.this,cp,yue);
                dialog.show(getSupportFragmentManager(),"");
                dialog.setData(new ZhczDialog.CzData() {
                    @Override
                    public void czdata() {
                        getData();
                    }
                });
            }
        });

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BZhglActivity.this, BZdglActivity.class));
            }
        });

    }

    private void getData() {

        VolleyTo volleyTo=new VolleyTo();
        volleyTo.setUrl("get_vehicle").setVolleyLo(new VolleyLo() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    list.clear();
                    JSONArray jsonArray=jsonObject.getJSONArray("ROWS_DETAIL");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        list.add(new ZhglInfor(jsonObject1.getString("brand"),
                                jsonObject1.getString("owner"),
                                jsonObject1.getString("plate"),
                                jsonObject1.getInt("balance")));
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }).start();
    }

    private void initView() {
        lv=findViewById(R.id.lv);
        pl=findViewById(R.id.title1);
        record=findViewById(R.id.title2);
        pl.setText("批量充值");
        record.setText("充值记录");
        TextView title=findViewById(R.id.title);
        title.setText("账户管理");
        list=new ArrayList<>();
        adapter=new ZhglAdapter(this,0,list);
        lv.setAdapter(adapter);
        ImageView change=findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppClient appClient= (AppClient) getApplication();

        for (int i=0;i<list.size();i++){
            list.get(i).setYz(appClient.getYz());
        }
        adapter.notifyDataSetChanged();
    }
}
