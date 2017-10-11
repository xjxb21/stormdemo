package com.xiao.demos.demo1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Description: User: xiaojixiang Date: 2017/9/30 Version: 1.0
 */
public class MyProducer_1 {

    public static void main(String[] args) {

        MyCallback myCallback = new MyCallback();

        try {
            Properties properties = new Properties();
            InputStreamReader inputStreamReader = new InputStreamReader(MyProducer_1.class.getClassLoader().getResourceAsStream("kafka-producer.properties"));
            properties.load(inputStreamReader);

            Producer<String, String> producer = new KafkaProducer<>(properties);
            for (int i = 0; i < 100; i++) {
                Future<RecordMetadata> sendFuture = producer.send(new ProducerRecord<>("my-topic", Integer.toString(i), "value_" + Integer.toString(i)), myCallback);
                sendFuture.get(2000, TimeUnit.MILLISECONDS);
                Thread.sleep(2000); //每2秒发送一次
            }

            producer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
