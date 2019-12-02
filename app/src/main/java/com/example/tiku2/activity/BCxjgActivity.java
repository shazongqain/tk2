package com.example.tiku2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tiku2.AppClient;
import com.example.tiku2.R;
import com.example.tiku2.adapter.XqAdapter;
import com.example.tiku2.adapter.ZlAdapter;
import com.example.tiku2.bean.XqInfor;
import com.example.tiku2.bean.ZlInfor;

import java.util.ArrayList;
import java.util.List;

public class BCxjgActivity extends AppCompatActivity {
    private List<ZlInfor> zlInfors;
    private List<XqInfor> xqInfors;
    private ListView lv_zl,lv_xq;
    private ZlAdapter zlAdapter;
    private XqAdapter xqAdapter;
    private List<XqInfor> mlist;
    private ImageView add;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bcxjg);
        initView();
        setListener();
    }

    private void setListener() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BCxjgActivity.this, BClwzActivity.class));
                finish();
            }
        });
        zlAdapter.setData(new ZlAdapter.SetData() {
            @Override
            public void setdata(int lx, int position) {

                if (lx==1){
                  removeitem(position);

                    if ((position-1)>=0){
                        findxq(position-1);
                    }else {
                           if (position+1<zlInfors.size()){
                               findxq(position+1);
                           }
                    }
                    zlInfors.remove(position);
                    zlAdapter.notifyDataSetChanged();
                }else {
                    findxq( position);
                }
            }
        });
        lv_xq.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(BCxjgActivity.this,BJkzpActivity.class));

            }
        });


    }

    private void removeitem(int position) {
        ZlInfor zlInfor=zlInfors.get(position);
        for (int i=mlist.size()-1;i>=0;i--){
            XqInfor xqInfor=mlist.get(i);
            if (zlInfor.getCp().equals(xqInfor.getCp())){
                mlist.remove(i);
            }
        }

        for (int i=xqInfors.size()-1;i>=0;i--){
            XqInfor xqInfor=xqInfors.get(i);
            if (zlInfor.getCp().equals(xqInfor.getCp())){
                xqInfors.remove(i);
            }
        }

       xqAdapter.notifyDataSetInvalidated();
    }

    private void findxq(int position) {
        ZlInfor infor=zlInfors.get(position);
        mlist.clear();
        for (int i=0;i<xqInfors.size();i++){
            XqInfor xqInfor=xqInfors.get(i);
            if (infor.getCp().equals(xqInfor.getCp())){
                mlist.add(xqInfor);
            }
        }
        xqAdapter.notifyDataSetInvalidated();
    }

    private void initView() {
        AppClient appClient= (AppClient) getApplication();
        zlInfors=appClient.getZlInfors();
        xqInfors=appClient.getXqInfors();
        mlist=new ArrayList<>();
        lv_zl=findViewById(R.id.lv_zl);
        lv_xq=findViewById(R.id.lv_xq);
        add=findViewById(R.id.add);
        zlAdapter=new ZlAdapter(this,0,zlInfors);
        xqAdapter=new XqAdapter(this,0,mlist);
        lv_zl.setAdapter(zlAdapter);
        lv_xq.setAdapter(xqAdapter);
        getFirst();
        TextView title=findViewById(R.id.title);
        title.setText("查询结果");
        ImageView change=findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getFirst() {
        ZlInfor zlInfor=zlInfors.get(0);
        for (int i=0;i<xqInfors.size();i++){
            XqInfor xqInfor=xqInfors.get(i);
            if (xqInfor.getCp().equals(zlInfor.getCp())){
                mlist.add(xqInfor);
            }
        }
        xqAdapter.notifyDataSetInvalidated();
    }

}
