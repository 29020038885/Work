package com.example.myhealthapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    private EditText user_id;

    private EditText user_password;

    private SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        //获取mSharedPreferences
        mSharedPreferences = getSharedPreferences("user", MODE_PRIVATE);

        //初始化控件
        user_id = findViewById(R.id.user_id);
        user_password = findViewById(R.id.user_password);

        //点击注册
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到注册页面
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //点击登录
        findViewById(R.id.login_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = user_id.getText().toString();
                String password = user_password.getText().toString();
                if (TextUtils.isEmpty(id) && TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                } else {
                    String default_id = mSharedPreferences.getString("id", null);
                    String default_pwd = mSharedPreferences.getString("password", null);
                    if (id.equals(default_id) && password.equals(default_pwd)) {
                        //登录成功
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}