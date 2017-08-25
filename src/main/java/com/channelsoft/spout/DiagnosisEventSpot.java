package com.channelsoft.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.tuple.Fields;
import com.channelsoft.coordinator.DefaultCoodinator;
import com.channelsoft.emitter.DiagnosisiEventEmitter;
import storm.trident.spout.ITridentSpout;

import java.util.Map;

/**
 * <dl>
 * <dt>${getClass}</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2016</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/8/25</dd>
 * </dl>
 *
 * @author lizhu
 */
public class DiagnosisEventSpot implements ITridentSpout<Long> {

    private static final long serialVersionUID = 1L;
    SpoutOutputCollector collector;
    BatchCoordinator<Long> coordinator = new DefaultCoodinator();
    Emitter<Long> emitter = new DiagnosisiEventEmitter();
    public BatchCoordinator<Long> getCoordinator(String txStateId, Map conf, TopologyContext context) {
        return  coordinator;
    }

    public Emitter<Long> getEmitter(String txStateId, Map conf, TopologyContext context) {
        return emitter;
    }

    public Map getComponentConfiguration() {
        return null;
    }

    public Fields getOutputFields() {
        return new Fields("event");
    }
}
