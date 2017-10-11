package com.xiao.util.test;

import com.xiao.util.test.model.EarthquakeData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: 一些测试工具
 * User: xiaojixiang
 * Date: 2017/9/27
 * Version: 1.0
 */

public class Utils {

    public static final String file_name = "E:\\projects\\IdeaProjects\\stormdemo\\commons\\src\\main\\resources\\eqList.txt";

    public static List<EarthquakeData> readData() throws IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("y-M-d H:m:s");
        List<String> collect = Files.lines(Paths.get(file_name)).collect(Collectors.toList());
        List<EarthquakeData> earthquakeDataList = collect.stream()
                .filter(s -> !s.trim().isEmpty())
                .map(s -> {
                    String[] data = s.split("\t");
                    EarthquakeData earthquakeData = new EarthquakeData();
                    earthquakeData.setOccurrenceTime(LocalDateTime.parse(data[0], dateTimeFormatter));
                    earthquakeData.setMagnitude(Float.parseFloat(data[1]));
                    earthquakeData.setLatitude(Float.parseFloat(data[2]));
                    earthquakeData.setLongitude(Float.parseFloat(data[3]));
                    earthquakeData.setDepth(Float.parseFloat(data[4]));
                    earthquakeData.setLocation(data[5]);
                    return earthquakeData;
                }).peek(earthquakeData -> System.out.println(earthquakeData.toString()))
                .collect(Collectors.toList());
        return earthquakeDataList;
    }

    //对象直接转换工具
    public static Object ByteToObject(byte[] bytes) {
        Object obj = null;
        try {
            // byteArray to object
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);

            obj = oi.readObject();
            bi.close();
            oi.close();
        } catch (Exception e) {
            System.out.println("translation " + e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }

    public static byte[] ObjectToByte(java.lang.Object obj) {
        byte[] bytes = null;
        try {
            // object to bytearray
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);

            bytes = bo.toByteArray();

            bo.close();
            oo.close();
        } catch (Exception e) {
            System.out.println("translation " + e.getMessage());
            e.printStackTrace();
        }
        return bytes;
    }

}
