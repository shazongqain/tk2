package com.example.tiku2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiku2.MyTouchListener;
import com.example.tiku2.R;

public class BTpsfActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.btpsf);
        ImageView imageView=findViewById(R.id.image);
        Button back=findViewById(R.id.back);
        switch (getIntent().getIntExtra("index",-1)){
            case 1:
                imageView.setImageResource(R.drawable.weizhang1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.weizhang2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.weizhang3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.weizhang4);
                break;
        }

        imageView.setOnTouchListener(new MyTouchListener(imageView));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

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


}
