package com.channelsoft.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

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
public class WordCountBolt extends BaseRichBolt {

    private OutputCollector collector;

    private HashMap<String,Long> counts = null;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector=collector;
        counts = new HashMap<String, Long>();
    }

    public void execute(Tuple input) {
        String word = input.getStringByField("word");
        Long count = counts.get(word);
        if(count != null){
             count = counts.get(word)+1;
            counts.put(word,count);
        }else{
            count = 1L;
            counts.put(word,count);
        }
        this.collector.emit(new Values(word,count));
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word","count"));
    }
}
