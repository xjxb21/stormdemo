package com.xiao.spout;

import com.xiao.util.test.model.EarthquakeData;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:Test spout
 * User: xiaojixiang
 * Date: 2017/9/24
 * Version: 1.0
 */

public class EarthquakeSpout extends BaseRichSpout {

    private static final Logger logger = LoggerFactory.getLogger(EarthquakeSpout.class);

    private SpoutOutputCollector outputCollector;

    List<EarthquakeData> source_Data = new ArrayList<>();

    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        logger.debug(this.getClass().getName() + ">>>open");
        //init parameters
        this.outputCollector = collector;
        //read data

        try {
            List earthquakeDatas = com.xiao.util.test.Utils.readData();
            this.source_Data = earthquakeDatas;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        logger.debug(this.getClass().getName() + ">>>close");
    }

    public void activate() {
        logger.debug(this.getClass().getName() + ">>>activate");

    }

    public void deactivate() {
        logger.debug(this.getClass().getName() + ">>>deactivate");

    }

    public void nextTuple() {
        logger.debug(Thread.currentThread().getName() + "" + System.currentTimeMillis());
        if (!source_Data.isEmpty()) {
            source_Data.stream().forEachOrdered(s -> {
                outputCollector.emit(new Values(s));
            });
        }

        source_Data.clear();
        Utils.sleep(1);
    }

    public void ack(Object msgId) {
        logger.debug(this.getClass().getName() + ">>>ack " + msgId.toString());

    }

    public void fail(Object msgId) {
        logger.debug(this.getClass().getName() + ">>>fail " + msgId.toString());

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        logger.debug(this.getClass().getName() + ">>>declareOutputFields");
        declarer.declare(new Fields("my_Spout_Field"));
    }
//
//    public Map<String, Object> getComponentConfiguration() {
//        return null;
//    }
}
