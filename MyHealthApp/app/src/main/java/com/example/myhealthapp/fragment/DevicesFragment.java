package com.example.myhealthapp.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myhealthapp.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DevicesFragment extends Fragment {

    private EditText editTextUserId;
    private EditText editTextDeviceName;
    private EditText editTextDeviceHeartRate;
    private EditText editTextDeviceStep;
    private EditText editTextDeviceTime;
    private Button buttonSubmitDeviceData;

    private TextView textViewSimulatedHeartRate;
    private TextView textViewSimulatedStep;
    private TextView textViewSimulatedTime; // 声明模拟时间的 TextView


    private Handler handler;
    private Runnable dataSimulationTask;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_devices, container, false);

        // 手动输入相关控件
        editTextUserId = view.findViewById(R.id.editUserId);
        editTextDeviceName = view.findViewById(R.id.editTextDeviceName);
        editTextDeviceHeartRate = view.findViewById(R.id.editTextDeviceHeartRate);
        editTextDeviceStep = view.findViewById(R.id.editTextDeviceStep);
        editTextDeviceTime = view.findViewById(R.id.editTextDeviceTime);
        buttonSubmitDeviceData = view.findViewById(R.id.buttonSubmitDeviceData);

        // 模拟数据显示控件
        textViewSimulatedHeartRate = view.findViewById(R.id.textViewSimulatedHeartRate);
        textViewSimulatedStep = view.findViewById(R.id.textViewSimulatedStep);
        textViewSimulatedTime = view.findViewById(R.id.textViewSimulatedTime); // 添加这一行初始化代码

        // 按钮点击事件：上传手动输入的数据
        buttonSubmitDeviceData.setOnClickListener(v -> {
            String userId = editTextUserId.getText().toString().trim();
            String name = editTextDeviceName.getText().toString().trim();
            String heartRate = editTextDeviceHeartRate.getText().toString().trim();
            String step = editTextDeviceStep.getText().toString().trim();
            String time = editTextDeviceTime.getText().toString().trim();

            if (userId.isEmpty() || name.isEmpty() || heartRate.isEmpty() || step.isEmpty() || time.isEmpty()) {
                Toast.makeText(getActivity(), "请完整填写所有数据", Toast.LENGTH_SHORT).show();
                return;
            }

            uploadHealthDataToDatabase(
                    Integer.parseInt(userId), name, Integer.parseInt(heartRate), Integer.parseInt(step), time);
        });

        // 开始模拟蓝牙设备数据生成
        startDataSimulation();

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (handler != null) {
            handler.removeCallbacks(dataSimulationTask); // 停止定时任务
        }
    }

    private void startDataSimulation() {
        handler = new Handler(Looper.getMainLooper());
        Random random = new Random();

        dataSimulationTask = new Runnable() {
            @Override
            public void run() {
                // 模拟数据
                int simulatedUserId = 1; // 固定一个用户ID，适配多用户时可动态生成
                String simulatedName = "SimulatedDevice";
                int simulatedHeartRate = 60 + random.nextInt(41); // 心率 60~100
                int simulatedStep = 100 + random.nextInt(401);    // 步数 100~500

                String simulatedTime = "2024-11-30T23:11:55";

                // 显示在页面上
                textViewSimulatedHeartRate.setText("心率：" + simulatedHeartRate);
                textViewSimulatedStep.setText("步数：" + simulatedStep);
                textViewSimulatedTime.setText("记录时间：" + simulatedTime);

                // 自动上传数据到数据库
                uploadHealthDataToDatabase(simulatedUserId, simulatedName, simulatedHeartRate, simulatedStep, simulatedTime);

                // 每隔 15 秒更新数据（模拟定时上传）
                handler.postDelayed(this, 15000);
            }
        };

        handler.post(dataSimulationTask); // 开始任务
    }

    private void uploadHealthDataToDatabase(int userId, String name, int heartRate, int step, String time) {
        OkHttpClient client = new OkHttpClient();

        // 构建 JSON 数据
        String json = "{"
                + "\"userid\":" + userId + ","
                + "\"name\":\"" + name + "\","
                + "\"time\":\"" + time + "\","
                + "\"heartrate\":" + heartRate + ","
                + "\"step\":" + step
                + "}";

        // 构建 RequestBody
        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));

        // 构建 POST 请求
        String url = "http://10.0.2.2:9090/healthdata";  // 服务器地址
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        // 异步请求
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(() ->
                        Toast.makeText(getActivity(), "上传失败：" + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                getActivity().runOnUiThread(() ->
                        Toast.makeText(getActivity(), "数据上传成功", Toast.LENGTH_SHORT).show()
                );
            }
        });
    }
}