package com.xiao.util.test.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description: 地震数据
 * User: xiaojixiang
 * Date: 2017/9/27
 * Version: 1.0
 */

public class EarthquakeData implements Serializable {

    LocalDateTime occurrenceTime;   //发生时间
    float magnitude;                //震级
    float latitude;                 //纬度
    float longitude;                //经度
    float depth;                    //深度
    String location;                //地点

    public LocalDateTime getOccurrenceTime() {
        return occurrenceTime;
    }

    public void setOccurrenceTime(LocalDateTime occurrenceTime) {
        this.occurrenceTime = occurrenceTime;
    }

    public float getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(float magnitude) {
        this.magnitude = magnitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "EarthquakeData{" +
                "occurrenceTime=" + occurrenceTime +
                ", magnitude=" + magnitude +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", depth=" + depth +
                ", location='" + location + '\'' +
                '}';
    }
}
