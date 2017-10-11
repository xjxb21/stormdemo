package com.xiao.spout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@DisplayName("Read file data")
class EarthquakeSpoutTest {

    final String file_name = "E:\\projects\\IdeaProjects\\stormdemo\\demo1\\src\\main\\resources\\data.txt";

    @BeforeEach
    void init() {

    }

    @Test
    void open() {
        try {
            Files.lines(Paths.get(file_name)).forEachOrdered(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}