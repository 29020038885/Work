package com.example.myhealthapp;

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

public class RegisterActivity extends AppCompatActivity {

    private EditText user_id;

    private EditText user_password;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        //获取mSharedPreferences
        mSharedPreferences = getSharedPreferences("user", MODE_PRIVATE);

        //初始化控件
        user_id = findViewById(R.id.user_id);
        user_password = findViewById(R.id.user_password);

        //返回
        findViewById(R.id.toolbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

        findViewById(R.id.register_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = user_id.getText().toString();
                String password = user_password.getText().toString();

                if(TextUtils.isEmpty(id) && TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                }
                else {

                    SharedPreferences.Editor edit = mSharedPreferences.edit();
                    edit.putString("id", id);
                    edit.putString("password", password);

                    //提交
                    edit.commit();
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }
        });
    }


}