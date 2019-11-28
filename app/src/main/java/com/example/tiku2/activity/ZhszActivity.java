package com.example.tiku2.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiku2.AppClient;
import com.example.tiku2.R;

public class ZhszActivity extends AppCompatActivity {
    private TextView tv_yz;
    private EditText et_yz;
    private Button set;
    private AppClient appClient;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhsz);
        initView();
      setListener();
    }

    private void setListener() {
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appClient.setYz(Integer.parseInt(et_yz.getText().toString()));
                Toast.makeText(ZhszActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                tv_yz.setText(appClient.getYz()+"");
            }
        });
    }


    private void initView() {
        tv_yz=findViewById(R.id.yz);
        et_yz=findViewById(R.id.et_yz);
        set=findViewById(R.id.set);
        appClient= (AppClient) getApplication();
        tv_yz.setText(appClient.getYz()+"");
        TextView title=findViewById(R.id.title);
        title.setText("账户设置");
        ImageView change=findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
