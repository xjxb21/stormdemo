package com.xiao.demos.demo3;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Properties;

/**
 * Description:
 * User: xiaojixiang
 * Date: 2017/10/4
 * Version: 1.0
 */

public class MyConsumer_3_1 {

    private static final String topic = "my-topic-3";


    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "192.168.8.101:9092,192.168.8.102:9092,192.168.8.103:9092");
        properties.setProperty("group.id", "test-3");               //group id
        properties.setProperty("enable.auto.commit", "true");       //自动提交
        //properties.setProperty("enable.auto.commit", "false");
        properties.setProperty("auto.commit.interval.ms", "1000");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        TopicPartition topicPartition = new TopicPartition(topic, 1);
        consumer.assign(Arrays.asList(topicPartition));

        while (true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
            consumerRecords.forEach((record) -> {
                System.out.println("receive >>>" + record.toString());
            });
        }
    }
}
