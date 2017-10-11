package com.xiao.demos.demo3;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Description: User: xiaojixiang Date: 2017/9/30 Version: 1.0
 */
public class MyProducer_3 {

    private static final String topic = "my-topic-3";

    public static void main(String[] args) throws InterruptedException {

        MyCallback myCallback = new MyCallback();

        try {
            Properties properties = new Properties();
            InputStreamReader inputStreamReader = new InputStreamReader(MyProducer_3.class.getClassLoader().getResourceAsStream("kafka-producer.properties"));
            properties.load(inputStreamReader);

            //数据发送分区规则
            properties.setProperty("partitioner.class", "com.xiao.demos.demo2.MyPartitioner");

            Producer<String, String> producer = new KafkaProducer<>(properties);
            for (int i = 0; i < 100; i++) {
                ProducerRecord data = new ProducerRecord(topic, Integer.toString(i), "value_" + Integer.toString(i));
                producer.send(data, myCallback);
                Thread.sleep(1000); //等待1秒，慢点发送
            }

            producer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
