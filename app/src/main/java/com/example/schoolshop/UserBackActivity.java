package com.example.schoolshop;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class UserBackActivity extends AppCompatActivity {

    Button     backbt1;
    TextView   etbkname;
    EditText   etbkmm1;
    EditText   etbkmm2;
    TextView   etbkquest;
    EditText   etbkansower;
    Button     zhbt;
    Handler    mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_back);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        find();
        etbkname.setText(bundle.getString("name"));
        etbkquest.setText(bundle.getString("question"));
        //返回
        backbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        zhbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = etbkname.getText().toString();
                final String pass = etbkmm1.getText().toString();
                final String ansower = etbkansower.getText().toString();
                StringBuffer temp = new StringBuffer("");
                if (etbkmm1.getText().toString().equals("")){
                    temp.append("密码");
                }
                if (etbkansower.getText().toString().equals("")){
                    temp.append("密保答案");
                }
                if(temp.toString().equals("")){
                    if(etbkmm1.getText().toString().equals(etbkmm2.getText().toString())){
                        //发送请求
                        mHandler = new Handler(){
                            public void handleMessage(Message msg){
                                if (msg.obj.toString().equals("答案错误")){
                                    Toast.makeText(UserBackActivity.this,"注册失败,密保答案错误",Toast.LENGTH_SHORT).show();
                                    etbkansower.setText("");
                                    etbkmm1.setText("");
                                    etbkmm2.setText("");
                                }else if (msg.obj.toString().equals("修改成功")){
                                    Toast.makeText(UserBackActivity.this,"密码重设成功",Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Toast.makeText(UserBackActivity.this,"网络连接失败，请检查网络",Toast.LENGTH_SHORT).show();
                                }
                            }

                        };
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        String result = sendokhttp(name,ansower,pass);
                                        Message m = mHandler.obtainMessage();
                                        m.obj = result;
                                        mHandler.sendMessage(m);
                                    }
                                }).start();
                            }
                        }).start();

                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }else{
                        Toast.makeText(UserBackActivity.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                        etbkmm1.setText("");
                        etbkmm2.setText("");
                    }
                }else{
                    temp.append(" 不能为空");
                    Toast.makeText(UserBackActivity.this,temp.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void find(){
        backbt1  = findViewById(R.id.fh1);
        etbkname = findViewById(R.id.bkusername);
        etbkmm1  = findViewById(R.id.bkmm1);
        etbkmm2  = findViewById(R.id.bkmm2);
        etbkquest = findViewById(R.id.bkquest);
        etbkansower = findViewById(R.id.bkansower);
        zhbt      = findViewById(R.id.zh);
    }

    public String sendokhttp(String name,String ansower , String pass){
        String responseData = null;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name",name);
            jsonObject.put("ansower",ansower);
            jsonObject.put("pass",pass);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType type = MediaType.parse("application/json;charset=utf-8");
        RequestBody RequestBody2 = RequestBody.create(type, "" + jsonObject.toString());

        try {
            OkHttpClient okhttpclient = new OkHttpClient();
            Request request = new Request.Builder()
                    // 指定访问的服务器地址
                    .url("http://120.79.198.138:8084/SchoolShop/BackServlet")
                    .post(RequestBody2)
                    .build();
            Response response = okhttpclient.newCall(request).execute();
            responseData = response.body().string();
            Log.d("RES", responseData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseData;
    }

}
