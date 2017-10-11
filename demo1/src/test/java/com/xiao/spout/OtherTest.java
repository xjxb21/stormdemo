package com.xiao.spout;

import com.xiao.model.EarthquakeData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Description:
 * User: xiaojixiang
 * Date: 2017/9/26
 * Version: 1.0
 */

public class OtherTest {

    final String file_name = "E:\\projects\\IdeaProjects\\stormdemo\\demo1\\src\\main\\resources\\eqList.txt";

    @Test
    public void test1() {
        try {
            List<String> list = Files.lines(Paths.get(file_name)).collect(Collectors.toList());
            System.out.println(list.isEmpty());
            list.clear();
            System.out.println(list.isEmpty());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws IOException {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("y-M-d H:m:s");

        List<String> collect = Files.lines(Paths.get(file_name)).collect(Collectors.toList());
        collect.stream()
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
    }
}
