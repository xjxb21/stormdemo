package com.xiao.blot;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Description:Test Bolt
 * User: xiaojixiang
 * Date: 2017/9/24
 * Version: 1.0
 */

public class MyBolt_1 implements IRichBolt {

    private static final Logger logger = LoggerFactory.getLogger(MyBolt_1.class);

    private OutputCollector collector;
    private static final String stream_id = "";

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        System.out.println(this.getClass().getName() + ">>>prepare");
        this.collector = collector;
    }

    public void execute(Tuple input) {
        logger.debug("**************************");
        System.out.println("MessageId:"+input.getMessageId());
        System.out.println("SourceComponent:"+input.getSourceComponent());
        System.out.println("SourceStreamId:"+input.getSourceStreamId());
        //logger.info("bolt_1 received tuple:" + input.getStringByField("my_Spout_Field"));
        System.out.println("receive spout:"+input.getStringByField("my_Spout_Field"));
        //collector.emit(new Values("***" + input.getStringByField("my_Spout_Field")));
        logger.debug("***********end************");

        //collector.emit(stream_id, new Values())
        Utils.sleep(1);
    }

    public void cleanup() {
        System.out.println(this.getClass().getName() + ">>>cleanup");
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        System.out.println(this.getClass().getName() + ">>>cleanup");
        declarer.declare(new Fields("myBolt_1_fields"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

}
