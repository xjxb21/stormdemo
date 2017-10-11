package com.xiao;

import com.xiao.blot.MyBolt_1;
import com.xiao.blot.MyBolt_2;
import com.xiao.spout.EarthquakeSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;

/**
 * Hello world!
 */
public class MyTopology {

    public static final String hello_world_topology = "my_topology_1";
    public static final String hello_world_spout1 = "my_spout_1";
    public static final String hello_world_bolt1 = "my_bolt_1";
    public static final String hello_world_bolt2 = "my_bolt_2";

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Config config = new Config();
        config.setNumWorkers(1);
        config.setDebug(true);

        TopologyBuilder topologyBuilder = new TopologyBuilder();
//        topologyBuilder.setSpout("hello_world_spout", new EarthquakeSpout(), 2);  //need test
        topologyBuilder.setSpout(hello_world_spout1, new EarthquakeSpout());
        topologyBuilder.setBolt(hello_world_bolt1, new MyBolt_1(), 2).shuffleGrouping(hello_world_spout1);
//        topologyBuilder.setBolt(hello_world_bolt1, new MyBolt_1()).shuffleGrouping(hello_world_spout1);
        topologyBuilder.setBolt(hello_world_bolt2, new MyBolt_2()).globalGrouping(hello_world_bolt1);
        StormTopology topology = topologyBuilder.createTopology();

        //local mode
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology(hello_world_topology, config, topology);

//        Utils.sleep(10 * 1000);
//
//        cluster.killTopology(hello_world_topology);
//        cluster.shutdown();
    }
}
