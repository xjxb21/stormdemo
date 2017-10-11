package com.xiao.demos.demo4;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * Description:
 * User: xiaojixiang
 * Date: 2017/10/8
 * Version: 1.0
 */

public class MyStream_Consumer_1 {

    private static final String topic = "my-stream-topic-2";


    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "192.168.8.101:9092,192.168.8.102:9092,192.168.8.103:9092");
        properties.setProperty("group.id", "stream-test-3");               //group id
        properties.setProperty("enable.auto.commit", "true");               //自动提交
        //properties.setProperty("enable.auto.commit", "false");
        properties.setProperty("auto.commit.interval.ms", "1000");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        //手动消费分区
//        TopicPartition topicPartition = new TopicPartition(topic, 0);
//        consumer.assign(Arrays.asList(topicPartition));

        consumer.subscribe(Arrays.asList(topic));

        while (true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(20);
            consumerRecords.forEach((record) -> {
                System.out.println("receive >>>" + record.toString());
            });
        }
    }
}
