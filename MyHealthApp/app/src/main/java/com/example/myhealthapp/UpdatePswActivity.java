package com.example.myhealthapp;

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

public class UpdatePswActivity extends AppCompatActivity {

    private EditText new_password;
    private EditText confirm_new_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_psw);


        //初始化控件
        new_password = findViewById(R.id.new_password);
        confirm_new_password = findViewById(R.id.confirm_new_password);

        //修改密码点击事件
        findViewById(R.id.bt_update_new_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_psw = new_password.getText().toString();
                String cfm_new_psw = confirm_new_password.getText().toString();

                if (TextUtils.isEmpty(new_psw) || TextUtils.isEmpty(cfm_new_psw)){
                    Toast.makeText(UpdatePswActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }else if ( !new_psw.equals(cfm_new_psw) ){
                    Toast.makeText(UpdatePswActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}