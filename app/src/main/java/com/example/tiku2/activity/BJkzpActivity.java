package com.example.tiku2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiku2.R;

public class BJkzpActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView wz1,wz2,wz3,wz4;
    private Button back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bjkzp);
        initView();
        setlistener();
    }

    private void setlistener() {
        wz4.setOnClickListener(this);
        wz3.setOnClickListener(this);
        wz2.setOnClickListener(this);
        wz1.setOnClickListener(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        wz1=findViewById(R.id.wz1);
        wz2=findViewById(R.id.wz2);
        wz3=findViewById(R.id.wz3);
        wz4=findViewById(R.id.wz4);
        back=findViewById(R.id.back);


        TextView content=findViewById(R.id.title);
        content.setText("监控抓拍");
        ImageView change=findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this, BTpsfActivity.class);
        switch (v.getId()){
            case R.id.wz1:
                intent.putExtra("index",1);
                break;
            case R.id.wz2:
                intent.putExtra("index",2);
                break;
            case R.id.wz3:
                intent.putExtra("index",3);
                break;
            case R.id.wz4:
                intent.putExtra("index",4);
                break;

        }
        startActivity(intent);
    }
}
