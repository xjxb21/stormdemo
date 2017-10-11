package com.xiao.demos.demo2;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;

/**
 * Description:
 * User: xiaojixiang
 * Date: 2017/10/8
 * Version: 1.0
 */

public class MyConsumerRebalanceListener implements ConsumerRebalanceListener {

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        System.out.println("test onPartitionsRevoked");
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        System.out.println("test onPartitionsAssigned");
    }
}
