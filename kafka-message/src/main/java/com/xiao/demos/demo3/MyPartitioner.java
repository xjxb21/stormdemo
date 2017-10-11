package com.xiao.demos.demo3;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * Description: 可以实现Partitioner接口配置数据发送的TOPIC分区的规则
 * User: xiaojixiang
 * Date: 2017/10/4
 * Version: 1.0
 */

public class MyPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        String strVal = value.toString().substring(value.toString().lastIndexOf("_") + 1);
        int v = Integer.parseInt(strVal);
        return v % 3;  //一共分3个分区
    }

    @Override
    public void close() {
        System.out.println("MyPartitioner close()");
    }

    @Override
    public void configure(Map<String, ?> configs) {
        System.out.println("MyPartitioner configure()");
    }

}
