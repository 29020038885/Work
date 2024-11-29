package com.example.mapper;

import com.example.entity.HealthData;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HealthDataMapper {

    // 查询所有数据
    @Select("SELECT * FROM healthdata")
    List<HealthData> getAllHealthData();

    // 通过ID查询
    @Select("SELECT * FROM healthdata WHERE id = #{id}")
    HealthData getHealthDataById(int id);

    // 插入新数据
    @Insert("INSERT INTO healthdata(userID, name, heartRate, step, time) VALUES(#{userID}, #{name}, #{heartRate}, #{step}, #{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertHealthData(HealthData healthData);

    // 更新数据
    @Update("UPDATE healthdata SET userID = #{userID}, name = #{name}, heartRate = #{heartRate}, step = #{step}, time = #{time} WHERE id = #{id}")
    void updateHealthData(HealthData healthData);

    // 删除数据
    @Delete("DELETE FROM healthdata WHERE id = #{id}")
    void deleteHealthData(int id);

    // 根据用户ID查询数据
    @Select("SELECT * FROM healthdata WHERE userID = #{userID} ORDER BY time DESC")
    List<HealthData> getHealthDataByUserID(int userID);
}
