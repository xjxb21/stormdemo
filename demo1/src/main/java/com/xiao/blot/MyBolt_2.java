package com.xiao.blot;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Description:Test Bolt
 * User: xiaojixiang
 * Date: 2017/9/24
 * Version: 1.0
 */

public class MyBolt_2 implements IRichBolt {

    private static final Logger logger = LoggerFactory.getLogger(MyBolt_2.class);

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        System.out.println(this.getClass().getName() + ">>>prepare");

    }

    public void execute(Tuple input) {
//        System.out.println("MessageId:"+input.getMessageId());
//        System.out.println("SourceComponent:"+input.getSourceComponent());
//        System.out.println("SourceStreamId:"+input.getSourceStreamId());
       logger.info(input.getStringByField("myBolt_1_fields"));
    }

    public void cleanup() {

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
