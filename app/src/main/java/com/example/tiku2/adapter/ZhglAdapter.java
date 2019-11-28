package com.example.tiku2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tiku2.R;
import com.example.tiku2.bean.ZhglInfor;

import java.util.List;

public class ZhglAdapter extends ArrayAdapter<ZhglInfor> {
    private SetData data;

    public void setData(SetData data) {
        this.data = data;
    }

    public ZhglAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.zhgl_item,null);
        TextView number=convertView.findViewById(R.id.number);
        TextView cp=convertView.findViewById(R.id.cp);
        TextView name=convertView.findViewById(R.id.name);
        TextView yue=convertView.findViewById(R.id.yue);
        ImageView image=convertView.findViewById(R.id.pinpai);
        LinearLayout item=convertView.findViewById(R.id.item);
        final CheckBox check=convertView.findViewById(R.id.check);
        Button cz=convertView.findViewById(R.id.cz);
        ZhglInfor infor = getItem(position);
        number.setText(position+1+"");
        cp.setText(infor.getCp());
        name.setText("车主:"+infor.getName());
        yue.setText("余额:"+infor.getYue()+"元");
        switch (infor.getPinpai()){
            case "奔驰":
                image.setImageResource(R.drawable.benci);
                break;
            case "宝马":
                image.setImageResource(R.drawable.bmw);
                break;
            case "中华":
                image.setImageResource(R.drawable.zhonghua);
                break;
            case "奥迪":
                image.setImageResource(R.drawable.mazida);
                break;
        }

        if (infor.getYz()>infor.getYue()){
            item.setBackgroundColor(Color.parseColor("#ffcc00"));
        }else {
            item.setBackgroundColor(Color.WHITE);
        }
        cz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(2,position,true);
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(1,position,check.isChecked());
            }
        });

        return convertView;
    }

    public  interface SetData{
        void setdata(int lx,int position,boolean check);
    }
}

