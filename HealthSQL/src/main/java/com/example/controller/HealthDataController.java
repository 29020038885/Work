package com.example.controller;

import com.example.entity.HealthData;
import com.example.service.HealthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/healthdata")
public class HealthDataController {

    @Autowired
    private HealthDataService healthDataService;

    // 获取所有健康数据
    @GetMapping
    public ResponseEntity<List<HealthData>> getAllHealthData() {
        List<HealthData> data = healthDataService.getAllHealthData();
        return ResponseEntity.ok(data);
    }

    // 根据ID获取健康数据
    @GetMapping("/{id}")
    public ResponseEntity<HealthData> getHealthDataById(@PathVariable int id) {
        HealthData healthData = healthDataService.getHealthDataById(id);
        return healthData != null ? ResponseEntity.ok(healthData) : ResponseEntity.notFound().build();
    }

    // 根据用户ID获取健康数据
    @GetMapping("/user/{userID}")
    public ResponseEntity<List<HealthData>> getHealthDataByUserID(@PathVariable int userID) {
        List<HealthData> data = healthDataService.getHealthDataByUserID(userID);
        return data.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(data);
    }

    // 新增健康数据
    @PostMapping
    public ResponseEntity<String> createHealthData(@RequestBody HealthData healthData) {
        // 简单校验
        if (healthData.getUserID() == 0 || healthData.getName() == null || healthData.getTime() == null) {
            return ResponseEntity.badRequest().body("Invalid input: userID, name, and time are required.");
        }
        healthDataService.insertHealthData(healthData);
        return ResponseEntity.ok("Data added successfully");
    }

    // 更新健康数据
    @PutMapping("/{id}")
    public ResponseEntity<String> updateHealthData(@PathVariable int id, @RequestBody HealthData healthData) {
        healthData.setId(id); // 保证 ID 不变
        if (healthData.getUserID() == 0 || healthData.getName() == null || healthData.getTime() == null) {
            return ResponseEntity.badRequest().body("Invalid input: userID, name, and time are required.");
        }
        healthDataService.updateHealthData(healthData);
        return ResponseEntity.ok("Data updated successfully");
    }

    // 删除健康数据
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHealthData(@PathVariable int id) {
        healthDataService.deleteHealthData(id);
        return ResponseEntity.ok("Data deleted successfully");
    }
}
