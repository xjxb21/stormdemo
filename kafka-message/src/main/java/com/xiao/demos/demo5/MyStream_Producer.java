package com.xiao.demos.demo5;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Description:
 * User: xiaojixiang
 * Date: 2017/10/8
 * Version: 1.0
 */

public class MyStream_Producer {

    //创建了3个分区(本地3个broker集群)
    //kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 2 --partitions 3 --topic my-stream-topic-2-test
    private static final String topic = "my-stream-topic-2-test";

    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();
        InputStreamReader inputStreamReader = new InputStreamReader(MyStream_Producer.class.getClassLoader().getResourceAsStream("kafka-producer-2.properties"));
        properties.load(inputStreamReader);

        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");

        KafkaProducer<String, Integer> producer = new KafkaProducer<>(properties);

        Map<String, Integer> identityHashMap = new IdentityHashMap<>();
        identityHashMap.put("xiao1", 10);
        identityHashMap.put("xiao2", 20);
        identityHashMap.put("xiao3", 20);
        identityHashMap.put("xiao4", 21);
        identityHashMap.put("xiao5", 11);
//        identityHashMap.put("xiao6", 22);
        identityHashMap.put("xiao6", 777);


        identityHashMap.forEach((s, integer) -> {
            //应该默认平均发送到3个分区中
            ProducerRecord<String, Integer> data = new ProducerRecord<>(topic, s, integer);
            producer.send(data);
            System.out.println(data.toString());
        });

        System.out.println("send finish..");

        producer.close();
    }
}
