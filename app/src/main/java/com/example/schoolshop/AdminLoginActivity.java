package com.example.schoolshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobScheduler;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class AdminLoginActivity extends AppCompatActivity {


    Button btpt;          //跳转管理员按钮
    TextView etadmin;           //用户名
    EditText  etadminpw;             //密码
    Button    btlogin;          //登录按钮
    String    adminname;
    String    adminpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        find();

        //普通用户跳转
        btpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLoginActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //登录
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminpass = etadminpw.getText().toString();
                if (adminpass.equals("admin")){
                    Intent intent = new Intent(AdminLoginActivity.this,AdminMuenActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    etadminpw.setText("");
                    Toast.makeText(AdminLoginActivity.this,"密码错误!",Toast.LENGTH_SHORT).show();
                }



            }
        });


    }

    public void find(){
        btpt    = findViewById(R.id.ptbt);
        etadmin = findViewById(R.id.adminusername);
        etadminpw = findViewById(R.id.adminuserpass);
        btlogin   = findViewById(R.id.adminlogin);
    }
}
