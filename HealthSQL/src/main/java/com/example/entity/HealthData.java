package com.example.entity;

import java.util.Date;

public class HealthData {
    private int id;
    private int userID;
    private String name;
    private int heartRate;
    private int step;
    private Date time;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

//    @Override
//    public String toString() {
//        return "HealthData{" +
//                "id=" + id +
//                ", userID=" + userID +
//                ", name='" + name + '\'' +
//                ", heartRate=" + heartRate +
//                ", step=" + step +
//                ", time=" + time +
//                '}';
//    }
}
