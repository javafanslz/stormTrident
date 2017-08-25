package com.channelsoft.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

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
public class SentenceSpout extends BaseRichSpout {

    private SpoutOutputCollector controller;

    private String[] sentences={"my dog has fleas","i like cold beverages","the dog ate my homework",
            "don't have a cow man","i don't think i like fleas"};
    private int index=0;

    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.controller=collector;
    }

    public void nextTuple() {
        this.controller.emit(new Values(sentences[index]));
        index++;
        if(index >= sentences.length){
            index=0;
        }
        Utils.sleep(1);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("sentence"));
    }
}
