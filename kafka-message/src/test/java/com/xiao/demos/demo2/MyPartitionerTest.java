package com.xiao.demos.demo2;

import com.xiao.util.test.Utils;
import com.xiao.util.test.model.EarthquakeData;
import junit.framework.TestCase;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.util.List;

/**
 * Description:
 * User: xiaojixiang
 * Date: 2017/10/4
 * Version: 1.0
 */
public class MyPartitionerTest extends TestCase {

    public void testPartition() throws Exception {
        String value = "value_100";
        assertEquals(value.substring(value.lastIndexOf("_") + 1), "100");
    }

    public void test2() {
        try {
            List<EarthquakeData> earthquakeData = Utils.readData();
            earthquakeData.stream().forEach(v -> {
                byte[] bytes = Utils.ObjectToByte(v);
                System.out.println(Utils.ByteToObject(bytes));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}