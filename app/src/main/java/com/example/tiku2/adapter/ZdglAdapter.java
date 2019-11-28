package com.example.tiku2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tiku2.R;
import com.example.tiku2.bean.ZdglInfor;

import java.util.List;

public class ZdglAdapter extends ArrayAdapter<ZdglInfor> {
    public ZdglAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.zdgl_item,null);
        TextView number=convertView.findViewById(R.id.number);
        TextView cp=convertView.findViewById(R.id.cp);
        TextView jine=convertView.findViewById(R.id.jine);
        TextView time=convertView.findViewById(R.id.time);
        TextView yue=convertView.findViewById(R.id.money);
        TextView name=convertView.findViewById(R.id.name);
        name.setText("admin");
        ZdglInfor infor = getItem(position);
        number.setText(position+1+"");
        cp.setText(infor.getCp());
        yue.setText(infor.getYue()+"");
        jine.setText(infor.getJine());
        time.setText(infor.getTime());
        return convertView;
    }
}
