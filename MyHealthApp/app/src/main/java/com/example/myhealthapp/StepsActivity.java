package com.example.myhealthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.myhealthapp.fragment.DevicesFragment;
import com.example.myhealthapp.fragment.HomeFragment;
import com.example.myhealthapp.fragment.MyInfoFragment;
import com.example.myhealthapp.fragment.PositionFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StepsActivity extends MainActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_steps);

        //返回首页
        findViewById(R.id.steps_BackHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });


    }
}