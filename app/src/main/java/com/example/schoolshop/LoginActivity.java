package com.example.schoolshop;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    Button    btadmin;          //跳转管理员按钮
    EditText  etuser;           //用户名
    EditText  etpw;             //密码
    Button    btlogin;          //登录按钮
    Button    btnew;            //跳转注册界面
    Button    btback;            //找回按钮
    Handler mHandler;
    Handler  handler1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        find();

        //跳转管理员监听
        btadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this,AdminLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //跳转新用户注册监听
        btnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this,CreatUserActivity.class);
                startActivity(intent);

            }
        });
        //跳转忘记密码
        btback.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                final String backname = etuser.getText().toString();
                if (etuser.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                }else{
                    handler1 = new Handler(){
                        public void handleMessage(Message msg){
                            if(msg.obj.equals("用户名不存在")) {
                                Toast.makeText(LoginActivity.this,"用户名不存在",Toast.LENGTH_SHORT).show();
                            }else if (msg.obj.equals("")){
                                Toast.makeText(LoginActivity.this,"网络连接失败，请检查网络",Toast.LENGTH_SHORT).show();
                            }else{
                                Intent intent = new Intent(LoginActivity.this,UserBackActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putCharSequence("name",etuser.getText().toString());
                                bundle.putCharSequence("question",msg.obj.toString());
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        }
                    };

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String result = sendback(backname);
                            Message m = handler1.obtainMessage();
                            m.obj = result;
                            handler1.sendMessage(m);
                        }
                    }).start();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }


            }
        });
        //登录跳转

        //进行验证

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etuser.getText().toString();
                final String userpsd  = etpw.getText().toString();
                //进行验证
                mHandler  = new Handler(){
                    public void handleMessage(Message msg){
                        if(msg.obj.equals("登陆成功")) {
                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this,MeunActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putCharSequence("User",etuser.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();

                        }else if (msg.obj.equals("用户名错误")){
                            Toast.makeText(LoginActivity.this,"用户名错误",Toast.LENGTH_SHORT).show();
                            etuser.setText("");
                            etpw.setText("");
                        }else if (msg.obj.equals("密码错误")){
                            Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                            etpw.setText("");
                        }else{
                            Toast.makeText(LoginActivity.this,"网络连接失败，请检查网络",Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String result = sendokhttp(username,userpsd);
                        Message m = mHandler.obtainMessage();
                        m.obj = result;
                        mHandler.sendMessage(m);

                    }
                }).start();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        });


    }

    public void find(){
        btadmin = findViewById(R.id.admbt);
        etuser  = findViewById(R.id.username);
        etpw    = findViewById(R.id.userpass);
        btlogin = findViewById(R.id.login);
        btnew   = findViewById(R.id.newuser);
        btback  = findViewById(R.id.userback);
        btnew.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        btback.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );


    }


    //访问服务器数据库
    public String sendokhttp(String name, String psd) {

        String responseData = null;
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("name", name);
            jsonObject.put("psd", psd);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        MediaType type = MediaType.parse("application/json;charset=utf-8");
        RequestBody RequestBody2 = RequestBody.create(type, "" + jsonObject.toString());

        try {
            OkHttpClient okhttpclient = new OkHttpClient();
            Request request = new Request.Builder()
                    // 指定访问的服务器地址
                    .url("http://120.79.198.138:8084/SchoolShop/LoginServlet")
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

    public String sendback(String name){
        String responseData = null;
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("name", name);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        MediaType type = MediaType.parse("application/json;charset=utf-8");
        RequestBody RequestBody2 = RequestBody.create(type, "" + jsonObject.toString());

        try {
            OkHttpClient okhttpclient = new OkHttpClient();
            Request request = new Request.Builder()
                    // 指定访问的服务器地址
                    .url("http://120.79.198.138:8084/SchoolShop/back1Servlet")
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
