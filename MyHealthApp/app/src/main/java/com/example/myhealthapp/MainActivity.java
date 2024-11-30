package com.example.myhealthapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.myhealthapp.fragment.DevicesFragment;
import com.example.myhealthapp.fragment.HomeFragment;
import com.example.myhealthapp.fragment.MyInfoFragment;
import com.example.myhealthapp.fragment.PositionFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private HomeFragment mHomeFragment;
    private PositionFragment mPositionFragment;
    private DevicesFragment mDevicesFragments;
    private MyInfoFragment mMyInfoFragments;

    private BottomNavigationView mBottomNavigationView;
    private TextView mTextViewResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 初始化控件
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mTextViewResponse = findViewById(R.id.textViewResponse); // 获取TextView

        // 设置点击事件
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.btm_nav_home) {
                    mTextViewResponse.setVisibility(TextView.VISIBLE);
                    selectedFragment(0);
                } else if (item.getItemId() == R.id.btm_nav_position) {
                    selectedFragment(1);
                    mTextViewResponse.setVisibility(TextView.GONE);
                } else if (item.getItemId() == R.id.btm_nav_devices) {
                    selectedFragment(2);
                    mTextViewResponse.setVisibility(TextView.GONE);
                } else {
                    selectedFragment(3);
                    mTextViewResponse.setVisibility(TextView.GONE);
                }
                return true;
            }
        });

        // 默认首页选中
        selectedFragment(0);
        mTextViewResponse.setVisibility(TextView.VISIBLE);
    }

    public void selectedFragment(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(fragmentTransaction);

        if (position == 0) {
            // 调用后端API
            callBackendApi();

            if (mHomeFragment == null) {
                mHomeFragment = new HomeFragment();
                fragmentTransaction.add(R.id.home_content, mHomeFragment);
            } else {
                fragmentTransaction.show(mHomeFragment);
            }
        } else if (position == 1) {
            if (mPositionFragment == null) {
                mPositionFragment = new PositionFragment();
                fragmentTransaction.add(R.id.home_content, mPositionFragment);
            } else {
                fragmentTransaction.show(mPositionFragment);
            }
        } else if (position == 2) {
            if (mDevicesFragments == null) {
                mDevicesFragments = new DevicesFragment();
                fragmentTransaction.add(R.id.home_content, mDevicesFragments);
            } else {
                fragmentTransaction.show(mDevicesFragments);
            }
        } else {
            if (mMyInfoFragments == null) {
                mMyInfoFragments = new MyInfoFragment();
                fragmentTransaction.add(R.id.home_content, mMyInfoFragments);
            } else {
                fragmentTransaction.show(mMyInfoFragments);
            }
        }

        // 提交
        fragmentTransaction.commit();
    }

    private void callBackendApi() {
        OkHttpClient client = new OkHttpClient();

        // 创建请求
        Request request = new Request.Builder()
                .url("http://10.0.2.2:9090/healthdata/1")
                .build();

        // 异步调用
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Failed to connect to server", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String responseData = response.body().string();
                    runOnUiThread(() -> {
                        // 显示API返回的 "Hello World" 信息
                        mTextViewResponse.setText(responseData);
                    });
                }
            }
        });
    }

    public void hideFragment(FragmentTransaction fragmentTransaction) {
        if (mHomeFragment != null) {
            fragmentTransaction.hide(mHomeFragment);
        }

        if (mPositionFragment != null) {
            fragmentTransaction.hide(mPositionFragment);
        }

        if (mDevicesFragments != null) {
            fragmentTransaction.hide(mDevicesFragments);
        }

        if (mMyInfoFragments != null) {
            fragmentTransaction.hide(mMyInfoFragments);
        }
    }
}
//给首页按钮添加了调用后端api的功能