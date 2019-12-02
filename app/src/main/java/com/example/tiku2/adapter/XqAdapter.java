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
import com.example.tiku2.bean.XqInfor;

import java.util.List;

public class XqAdapter extends ArrayAdapter<XqInfor> {
    public XqAdapter(@NonNull Context context, int resource, @NonNull List<XqInfor> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.bxqitem,null);
        TextView time=convertView.findViewById(R.id.time);
        TextView road=convertView.findViewById(R.id.road);
        TextView message=convertView.findViewById(R.id.message);
        TextView kf=convertView.findViewById(R.id.kf);
        TextView fk=convertView.findViewById(R.id.fk);
        XqInfor infor = getItem(position);
        time.setText(infor.getTime());
        road.setText(infor.getRoad());
        message.setText(infor.getInfor());
        kf.setText(infor.getKf()+"");
        fk.setText(infor.getFk()+"");
        return convertView;
    }
}
