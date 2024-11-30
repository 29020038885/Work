package com.example.mapper;

import com.example.entity.HealthData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HealthDataMapper {
    List<HealthData> getAllHealthData();

    HealthData getHealthDataById(int id);

    void insertHealthData(HealthData healthData);

    void updateHealthData(HealthData healthData);

    void deleteHealthData(int id);

    List<HealthData> getHealthDataByUserID(int userid);
}
