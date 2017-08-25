package com.channelsoft.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
public class AgentItemBolt extends BaseRichBolt {

    private OutputCollector outputCollector;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        Set configSet = stormConf.keySet();
        Iterator it=configSet.iterator();
        while(it.hasNext()){
            Object str=it.next();
            System.out.println(str+"="+stormConf.get(str));
        }
        System.out.println("context.getThisComponentId()="+context.getThisComponentId());
        System.out.println("context.toJSONString()="+context.toJSONString());
        this.outputCollector = collector;
    }

    public void execute(Tuple input) {
        String agentItem = input.getString(0);
        System.out.println("input为"+agentItem);
        this.outputCollector.emit(new Values(agentItem));
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}
