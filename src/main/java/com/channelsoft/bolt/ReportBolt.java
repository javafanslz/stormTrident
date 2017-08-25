package com.channelsoft.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

import java.util.*;

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
public class ReportBolt extends BaseRichBolt {

    private HashMap<String,Long> counts = null;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.counts = new HashMap<String, Long>();
    }

    public void execute(Tuple input) {
        String word = input.getStringByField("word");
        Long count = input.getLongByField("count");
        this.counts.put(word,count);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }


    public void cleanup() {
        System.out.println("--输出结果---");
        List<String> keys =new ArrayList<String>();
        keys.addAll(counts.keySet());
        Collections.sort(keys);
        for(String key : keys){
            System.out.println(key+"的数量为"+counts.get(key));
        }
        System.out.println("--输出结果结束---");
    }
}
