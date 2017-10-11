package com.xiao.demos.demo5;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;

import java.io.IOException;
import java.util.Properties;
import java.util.function.Consumer;

/**
 * Description: kafka stream test
 * User: xiaojixiang
 * Date: 2017/10/8
 * Version: 1.0
 */

public class MyStream_Demo2_kTable {


    private static final String fromTopic_1 = "my-stream-topic-2-test";

    private static final String toTopic = "my-stream-topic-3";  //not create
    private static final String tableTopic = "table-topic-1";

    public static void main(String[] args, KeyValueIterator<String, Integer> all) throws IOException, InterruptedException {

        Properties properties = new Properties();
        properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "myStream-test-2");
        properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.8.101:9092,192.168.8.102:9092,192.168.8.103:9092");
        properties.setProperty(StreamsConfig.CLIENT_ID_CONFIG, "myStreamClient-2");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());
        //默认为1个线程处理
        properties.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 2);

        KStreamBuilder builder = new KStreamBuilder();

        //KStream<String, Integer> myKStream = builder.stream(fromTopic_1);
        //窥视一下数据
//        myKStream.peek((key, value) -> System.out.println(Thread.currentThread().getName() + ">>>myKStream>>>key:" + key + ", value:" + value))
//                .to(Serdes.String(), Serdes.Integer(), toTopic);

        //注意:KTable与KStream不允许使用同一个TOPIC !!
        //KTable<String, Integer> mytable_1 = builder.table(fromTopic_1, "tableStore-1");

        GlobalKTable<Object, Object> global_mytable_1 = builder.globalTable(fromTopic_1, "global-tableStore-1");


        //启动kafkaStream
        KafkaStreams streams = new KafkaStreams(builder, properties);

        //task解释：
        //Kafka Stream的并行模型中，最小粒度为Task，而每个Task包含一个特定子Topology的所有Processor。
        //因此每个Task所执行的代码完全一样，唯一的不同在于所处理的数据集互补。
        //如果某个Stream的输入Topic有多个(比如2个Topic，1个Partition数为4，另一个Partition数为3)，
        //则总的Task数等于Partition数最多的那个Topic的Partition数（max(4,3)=4）。
        //这是因为Kafka Stream使用了Consumer的Rebalance机制，每个Partition对应一个Task。

        //可以通过设置配置参数(num.stream.threads,默认为1)，启动N个线程运行运行所有task
        streams.start();

//        while (true) {
//            //等待一会
//            Thread.sleep(3000);
//            System.out.println("===========================");
//            ReadOnlyKeyValueStore<String, Integer> store = streams.store("tableStore-1", QueryableStoreTypes.keyValueStore());
//            KeyValueIterator<String, Integer> all = store.all();
//            all.forEachRemaining(new Consumer<KeyValue<String, Integer>>() {
//                @Override
//                public void accept(KeyValue<String, Integer> v) {
//                    System.out.println("KTable>>>"+v.key + " === " + v.value);
//                }
//            });
//        }


        while (true) {
            //等待一会
            Thread.sleep(3000);
            System.out.println("===========================");
            ReadOnlyKeyValueStore<String, Integer> store = streams.store("global-tableStore-1", QueryableStoreTypes.keyValueStore());
            all.forEachRemaining(new Consumer<KeyValue<String, Integer>>() {
                @Override
                public void accept(KeyValue<String, Integer> v) {
                    System.out.println("Global KTable>>>" + v.key + " === " + v.value);
                }
            });
        }

    }
}
