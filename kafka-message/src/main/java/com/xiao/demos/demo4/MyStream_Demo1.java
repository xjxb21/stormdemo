package com.xiao.demos.demo4;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiao.util.test.Utils;
import com.xiao.util.test.model.EarthquakeData;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.KeyValueMapper;

import java.io.IOException;
import java.util.Properties;

/**
 * Description: kafka stream test
 * User: xiaojixiang
 * Date: 2017/10/8
 * Version: 1.0
 */

public class MyStream_Demo1 {


    private static final String fromTopic = "my-stream-topic-1-test";
    private static final String toTopic = "my-stream-topic-2";

    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        Properties properties = new Properties();
        properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "myStream-test-1");
        properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.8.101:9092,192.168.8.102:9092,192.168.8.103:9092");
        properties.setProperty(StreamsConfig.CLIENT_ID_CONFIG, "myStreamClient-1");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.ByteArray().getClass());

        KStreamBuilder builder = new KStreamBuilder();
        //test byte array
        //KStream<String, byte[]> textLinesStream = builder.stream(Serdes.String(), Serdes.ByteArray(), "my-stream-topic-1");
        KStream<String, byte[]> textLinesStream = builder.stream(fromTopic);

        KStream<String, String> kStream = textLinesStream.map((KeyValueMapper<String, byte[], KeyValue<String, String>>) (String key, byte[] value) -> {
            try {
                EarthquakeData earthquakeData = (EarthquakeData) Utils.ByteToObject(value);
                //convert to json string
                return new KeyValue<>(earthquakeData.getOccurrenceTime().toString(), mapper.writeValueAsString(earthquakeData));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                //此处不知道怎么处理更好
                return KeyValue.pair("-1", null);
            }
        }).filterNot((key, value) -> "-1".equals(key));
        kStream.to(Serdes.String(), Serdes.String(), toTopic);

        //启动kafkaStream
        KafkaStreams streams = new KafkaStreams(builder, properties);
        streams.start();
    }
}
