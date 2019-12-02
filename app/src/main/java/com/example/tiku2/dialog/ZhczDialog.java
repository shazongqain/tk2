package com.example.tiku2.dialog;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.tiku2.R;
import com.example.tiku2.net.VolleyLo;
import com.example.tiku2.net.VolleyTo;
import com.example.tiku2.sql.Mysql;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("ValidFragment")
public class ZhczDialog extends DialogFragment {
    private Context context;
    private String yue,cp;
    private Button sure,close;
    private Mysql mysql;
    private SQLiteDatabase database;
    private EditText et_jine;
    private ProgressDialog dialog;
    private CzData data;
    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage( Message msg) {
            dialog.dismiss();
            Toast.makeText(context, "充值成功", Toast.LENGTH_SHORT).show();
            return false;
        }
    });

    public void setData(CzData data) {
        this.data = data;
    }

    public ZhczDialog(Context context, String cp, String yue) {
        this.context = context;

        this.yue = yue;
        this.cp=cp;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bzhcz_dialog,null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setListener();
    }

    private void setListener() {
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_jine.getText().toString())){
                    Toast.makeText(context, "请输入充值金额", Toast.LENGTH_SHORT).show();
                    return;
                }
                setData();

            }
        });

        et_jine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_jine.getText().toString().startsWith("0")){
                    et_jine.setText("");
                    Toast.makeText(context, "金额不能为0", Toast.LENGTH_SHORT).show();
                }
                if (et_jine.getText().toString().length()>3){
                    et_jine.setText(et_jine.getText().toString().substring(0,3));
                    et_jine.setSelection(3);
                    Toast.makeText(context, "金额不能大于999元", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setData() {
        final String[] body=cp.split(" ");
        final String[] jines= yue.split(" ");
        for (int i=0;i<body.length;i++){
            VolleyTo volleyTo=new VolleyTo();
            final int finalI = i;
            final int finalI1 = i;
            volleyTo.setUrl("set_balance").setJsonObject("plate",body[i]).setJsonObject("balance",et_jine.getText().toString()).setVolleyLo(new VolleyLo() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    try {
                        if (jsonObject.getString("RESULT").equals("S")){
                            Date date=new Date(System.currentTimeMillis());
                            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd hh:mm");

                            ContentValues values=new ContentValues();
                            values.put("cp",body[finalI]);
                            values.put("name","admin");
                            values.put("jine",et_jine.getText().toString());
                            values.put("yue",Integer.parseInt(jines[finalI])+Integer.parseInt(et_jine.getText().toString()));
                            values.put("time",dateFormat.format(date));
                            database.insert("bzhgl",null,values);
                            if (finalI1==body.length-1){
                                dialog=new ProgressDialog(context);
                                dialog.setTitle("提示");
                                dialog.setMessage("网络请求中......");
                                dialog.show();
                                handler.sendEmptyMessageDelayed(0,1500);
                                data.czdata();
                                dismiss();

                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            }).start();
        }

    }

    private void initView() {
        sure=getView().findViewById(R.id.cz);
        close=getView().findViewById(R.id.close);
       TextView tv_cp=getView().findViewById(R.id.cp);
        et_jine=getView().findViewById(R.id.et_jine);
        tv_cp.setText(cp);
         mysql=new Mysql(context,"bzhgl.db",null,1);
        database=mysql.getWritableDatabase();

    }
    public interface CzData{
        void czdata();
    }
}
