package com.example.tiku2.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tiku2.R;
import com.example.tiku2.adapter.ZdglAdapter;
import com.example.tiku2.bean.ZdglInfor;
import com.example.tiku2.sql.Mysql;

import java.util.ArrayList;
import java.util.List;

public class BZdglActivity extends AppCompatActivity {
    private ListView lv;
    private List<ZdglInfor> list;
    private ZdglAdapter adapter;
    private Mysql mysql;
    private SQLiteDatabase database;
    private Spinner spinner;
    private Button cx;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bzdgl);
        initView();
        setListener();
    }

    private void setListener() {
        cx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("vvvvvvvvvv", "setListener: "+spinner.getSelectedItem().toString());
                setData();
            }
        });
    }

    private void setData() {
        Cursor cursor= database.rawQuery("select * from zhgl" ,null);
        if (cursor.moveToFirst()){
            list.clear();
            String cp,name,money,jine,time;
            do {
                cp=cursor.getString(cursor.getColumnIndex("cp"));
                name=cursor.getString(cursor.getColumnIndex("name"));
                jine=cursor.getString(cursor.getColumnIndex("jine"));
                money=cursor.getString(cursor.getColumnIndex("yue"));
                time=cursor.getString(cursor.getColumnIndex("time"));
                if (spinner.getSelectedItem().toString().equals("时间降序")){
                    Log.d("rrrrrrrrrrr", "setData: 时间降序");
                    list.add(0,new ZdglInfor(cp,jine,name,time,money));
                }else {
                    Log.d("rrrrrrrrrrr", "setData: 时间升序");
                    list.add(new ZdglInfor(cp,jine,name,time,money));
                }

            }while (cursor.moveToNext());

            adapter.notifyDataSetChanged();
        }

    }

    private void initView() {
        lv=findViewById(R.id.lv);
        list=new ArrayList<>();
        mysql=new Mysql(this,"bzhgl.db",null,1);
        database=mysql.getReadableDatabase();
        spinner=findViewById(R.id.spinner);
        cx=findViewById(R.id.cx);
        adapter=new ZdglAdapter(this,0,list);
        lv.setAdapter(adapter);
        TextView title=findViewById(R.id.title);
        title.setText("账单管理");
        TextView set=findViewById(R.id.title2);
        set.setText("账户设置");
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BZdglActivity.this, BZhszActivity.class));
            }
        });
        title.setText("账户设置");
        ImageView change=findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Log.d("vvvvvvvvvv", "initview: "+spinner.getSelectedItem().toString());
       setData();

    }
}
