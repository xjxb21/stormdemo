package com.xiao.demos.demo2;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

/**
 * Description:
 * User: xiaojixiang
 * Date: 2017/10/4
 * Version: 1.0
 */

public class MyConsumer_2 {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "192.168.8.101:9092,192.168.8.102:9092,192.168.8.103:9092");
        properties.setProperty("group.id", "test-2");               //group id
        properties.setProperty("enable.auto.commit", "true");       //自动提交
        //properties.setProperty("enable.auto.commit", "false");
        properties.setProperty("auto.commit.interval.ms", "1000");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
//        consumer.subscribe(Collections.singletonList("my-topic-2"));

        //测试ConsumerRebalanceListener接口
        consumer.subscribe(Collections.singletonList("my-topic-2"), new MyConsumerRebalanceListener());
        while (true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(200);
            consumerRecords.forEach((record) -> {
                System.out.println("receive >>>" + record.toString());
            });
        }
    }
}
