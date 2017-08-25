package com.channelsoft.topology;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import com.channelsoft.bolt.AgentItemBolt;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.StringScheme;
import storm.kafka.ZkHosts;

/**
 * <dl>
 * <dt>${getClass}</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2016</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/8/24</dd>
 * </dl>
 *
 * @author lizhu
 */
public class ChatLogTopology {
    public static void main(String[] args) {
        String topoName;
        String zkHost;
        String topic;
        String topicPath;
        if(args != null && args.length > 0){
            topoName=args[0];
            zkHost=args[1];
            topic=args[2];
            topicPath=args[3];
        }else{
            topoName="chatLogTopo";
            zkHost="node1,node2,node3";
            topic="chatLog";
            topicPath="/brokers";
        }
        String zookeeperPath = "";
        String[] node=zkHost.split(",");
        for(int i=0;i<node.length;i++){
            zookeeperPath += node[i]+":2181,";
        }
        zookeeperPath = zookeeperPath.substring(0,zookeeperPath.length()-1);

        ZkHosts zkHosts = new ZkHosts(zookeeperPath);

        SpoutConfig spoutConfig = new SpoutConfig(zkHosts,topic,topicPath,topoName);
        spoutConfig.forceFromStart=false;
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());

        KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);
        TopologyBuilder topologyBuilder = new TopologyBuilder();

        topologyBuilder.setSpout("kafkaSpout",kafkaSpout);
        topologyBuilder.setBolt("bolt",new AgentItemBolt()).shuffleGrouping("kafkaSpout");

        LocalCluster localCluster = new LocalCluster();
        Config config = new Config();
        localCluster.submitTopology(topoName,config,topologyBuilder.createTopology());
    }
}
