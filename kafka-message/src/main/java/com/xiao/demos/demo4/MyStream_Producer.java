package com.xiao.demos.demo4;

import com.xiao.util.test.Utils;
import com.xiao.util.test.model.EarthquakeData;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

/**
 * Description:
 * User: xiaojixiang
 * Date: 2017/10/8
 * Version: 1.0
 */

public class MyStream_Producer {

    private static final String topic = "my-stream-topic-1-test";

    public static void main(String[] args) throws IOException {

        List<EarthquakeData> earthquakeData = Utils.readData();

        Properties properties = new Properties();
        InputStreamReader inputStreamReader = new InputStreamReader(MyStream_Producer.class.getClassLoader().getResourceAsStream("kafka-producer-2.properties"));
        properties.load(inputStreamReader);

        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");

        KafkaProducer<String, byte[]> producer = new KafkaProducer<>(properties);

        //是否需要将其转换成字符串试试？
        earthquakeData.stream().forEach(v -> {
            ProducerRecord<String, byte[]> data = new ProducerRecord<>(topic, Utils.ObjectToByte(v));
            producer.send(data);
        });

        System.out.println("send finish..");
    }
}
