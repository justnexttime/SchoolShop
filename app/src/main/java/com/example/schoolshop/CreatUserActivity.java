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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.schoolshop.entity.User;

import org.json.JSONException;
import org.json.JSONObject;

public class CreatUserActivity extends AppCompatActivity {

    Button     backbt;
    EditText   etnewname;
    RadioGroup rdsex;
    EditText   etnewmm1;
    EditText   etnewmm2;
    EditText   etnewtel;
    EditText   etnewquest;
    EditText   etnewansower;
    EditText   etnewadress;
    Button     zcbt;
    Handler    mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_user);
        find();

        //返回跳转
        backbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        zcbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //性别的取值
                String sex = null;
                for (int i = 0;i<rdsex.getChildCount();i++){
                    RadioButton r = (RadioButton) rdsex.getChildAt(i);
                    if (r.isChecked()){
                        sex = r.getText().toString();
                        break;
                    }
                }
                //判断用户名是否为空
                //判断密码两次一致
                StringBuffer temp = new StringBuffer("");
                if(etnewname.getText().toString().equals("")){
                    temp.append("用户名");
                }
                if (etnewmm1.getText().toString().equals("")){
                    temp.append("密码");
                }

                if (etnewtel.getText().toString().equals("")){
                    temp.append("电话");
                }
                if(etnewquest.getText().toString().equals("")){
                    temp.append("密保问题");
                }
                if (etnewansower.getText().toString().equals("")){
                    temp.append("密保答案");
                }
                if (etnewadress.getText().toString().equals("")){
                    temp.append("地址");
                }
                if (temp.toString().equals("")){
                    //都不为空时判断密码一致吗
                    if (etnewmm1.getText().toString().equals(etnewmm2.getText().toString())){
                        //这里格式正确才能开始注册请求
                        final User user = new User();
                        user.setUserName(etnewname.getText().toString());
                        user.setUserPassworld(etnewmm1.getText().toString());
                        user.setSex(sex);
                        user.setTel(etnewtel.getText().toString());
                        user.setQuention(etnewquest.getText().toString());
                        user.setAnsower(etnewansower.getText().toString());
                        user.setAdress(etnewadress.getText().toString());
                        user.setMoney("10");

                        //handler
                        mHandler = new Handler(){
                            public  void handleMessage(Message msg){
                                if (msg.obj.toString().equals("注册失败，用户名存在")){
                                    Toast.makeText(CreatUserActivity.this,"注册失败，用户名存在",Toast.LENGTH_SHORT).show();
                                    etnewname.setText("");
                                }else if (msg.obj.toString().equals("注册成功")){
                                    Toast.makeText(CreatUserActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Toast.makeText(CreatUserActivity.this,"网络连接失败，请检查网络",Toast.LENGTH_SHORT).show();
                                }

                            }
                        };
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String result = sendokhttp(user.getUserName(),user.getUserPassworld(),user.getSex(),user.getTel(),user.getQuention(),user.getAnsower(),user.getAdress(),user.getMoney());
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

                    }else{
                        //密码不一致
                        etnewmm1.setText("");
                        etnewmm2.setText("");
                        Toast.makeText(CreatUserActivity.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    temp.append("不能为空");
                    Toast.makeText(CreatUserActivity.this,temp.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void find(){
        backbt    = findViewById(R.id.fh);
        etnewname = findViewById(R.id.newusername);
        rdsex     = findViewById(R.id.newsex);
        etnewmm1  = findViewById(R.id.newmm1);
        etnewmm2  = findViewById(R.id.newmm2);
        etnewtel  = findViewById(R.id.newtel);
        etnewquest = findViewById(R.id.newquest);
        etnewansower = findViewById(R.id.newquest);
        etnewadress  = findViewById(R.id.newaddress);
        zcbt      = findViewById(R.id.zc);
    }

    public String sendokhttp(String name , String pass ,String sex , String tel , String question , String ansower , String adress,String money){
        String responseData = null;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username",name);
            jsonObject.put("userpass",pass);
            jsonObject.put("sex",sex);
            jsonObject.put("tel",tel);
            jsonObject.put("question",question);
            jsonObject.put("ansower",ansower);
            jsonObject.put("adress",adress);
            jsonObject.put("money",money);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType type = MediaType.parse("application/json;charset=utf-8");
        RequestBody RequestBody2 = RequestBody.create(type, "" + jsonObject.toString());
        try {
            OkHttpClient okhttpclient = new OkHttpClient();
            Request request = new Request.Builder()
                    // 指定访问的服务器地址
                    .url("http://120.79.198.138:8084/SchoolShop/RegisterServlet")
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
