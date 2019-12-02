package com.example.tiku2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tiku2.R;
import com.example.tiku2.bean.ZlInfor;

import java.util.List;

public class ZlAdapter extends ArrayAdapter<ZlInfor> {
    private SetData data;

    public void setData(SetData data) {
        this.data = data;
    }

    public ZlAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.bzlitem,null);
        TextView wcl=convertView.findViewById(R.id.wcl);
        TextView kf=convertView.findViewById(R.id.kf);
        TextView fk=convertView.findViewById(R.id.fk);
        LinearLayout item=convertView.findViewById(R.id.item);
        ImageView jianhao=convertView.findViewById(R.id.jianhao);
        TextView cp=convertView.findViewById(R.id.cp);
        ZlInfor infor = getItem(position);
        wcl.setText(infor.getWcl()+"");
        fk.setText(infor.getFk()+"");
        kf.setText(infor.getKf()+"");
        cp.setText("鲁"+infor.getCp());
        jianhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(1,position);
            }
        });
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setdata(2,position);
            }
        });
        return convertView;
    }
    public  interface SetData{
        void setdata(int lx,int position);
    }
}
