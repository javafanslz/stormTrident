package com.channelsoft.topology;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;
import com.channelsoft.bolt.ReportBolt;
import com.channelsoft.bolt.SplitSentenceBolt;
import com.channelsoft.bolt.WordCountBolt;
import com.channelsoft.spout.SentenceSpout;

/**
 * <dl>
 * <dt>${getClass}</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2016</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/8/22</dd>
 * </dl>
 *
 * @author lizhu
 */
public class WordCountTopology {

    public static void main(String[] args) throws InterruptedException, AlreadyAliveException, InvalidTopologyException {
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        topologyBuilder.setSpout("sentence-spot",new SentenceSpout());
        topologyBuilder.setBolt("splitcount",new SplitSentenceBolt()).shuffleGrouping("sentence-spot");
        topologyBuilder.setBolt("wordcount",new WordCountBolt()).fieldsGrouping("splitcount",new Fields("word"));
        topologyBuilder.setBolt("reportcount",new ReportBolt()).globalGrouping("wordcount");

        Config config = new Config();

        if(args.length != 0){
            StormSubmitter.submitTopology(args[0],config,topologyBuilder.createTopology());
        }else{
            LocalCluster localCluster = new LocalCluster();
            localCluster.submitTopology("wordToplo",config,topologyBuilder.createTopology());
            Utils.sleep(20000);
            localCluster.killTopology("wordToplo");
            localCluster.shutdown();
        }
    }

}
