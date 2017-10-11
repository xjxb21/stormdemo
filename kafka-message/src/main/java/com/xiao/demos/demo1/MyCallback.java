package com.xiao.demos.demo1;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * Description:
 * User: xiaojixiang
 * Date: 2017/10/2
 * Version: 1.0
 */

public class MyCallback implements Callback {

    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {

        if (exception != null) {
            System.out.println("topic '" + metadata.topic() + "' send error>>>" + metadata.toString() + ">>> error:" + exception.getMessage());
        } else {
            System.out.println("topic '" + metadata.topic() + "' send successful >>> offset:" + metadata.offset() + ">>> partition:" + metadata.partition());
        }
    }
}
