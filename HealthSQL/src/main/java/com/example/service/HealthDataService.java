package com.example.service;

import com.example.mapper.HealthDataMapper;
import com.example.entity.HealthData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthDataService {

    @Autowired
    private HealthDataMapper healthDataMapper;

    // 获取所有健康数据
    public List<HealthData> getAllHealthData() {
        return healthDataMapper.getAllHealthData();
    }

    // 通过ID查询健康数据
    public HealthData getHealthDataById(int id) {
        return healthDataMapper.getHealthDataById(id);
    }

    // 根据用户ID获取健康数据
    public List<HealthData> getHealthDataByUserID(int userID) {
        return healthDataMapper.getHealthDataByUserID(userID);
    }

    // 插入新的健康数据
    public void insertHealthData(HealthData healthData) {
        // 检查必要字段
        if (healthData.getUserID() == 0 || healthData.getName() == null || healthData.getTime() == null) {
            throw new IllegalArgumentException("Invalid input: userID, name, and time are required.");
        }
        healthDataMapper.insertHealthData(healthData);
    }

    // 更新健康数据
    public void updateHealthData(HealthData healthData) {
        // 检查必要字段
        if (healthData.getUserID() == 0 || healthData.getName() == null || healthData.getTime() == null) {
            throw new IllegalArgumentException("Invalid input: userID, name, and time are required.");
        }
        healthDataMapper.updateHealthData(healthData);
    }

    // 删除健康数据
    public void deleteHealthData(int id) {
        healthDataMapper.deleteHealthData(id);
    }
}
