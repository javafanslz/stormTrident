package com.channelsoft.stateFactory;


import backtype.storm.task.IMetricsContext;
import com.channelsoft.state.OutbreakeTrendBackingMap;
import com.channelsoft.state.OutbreakeTrendState;
import storm.trident.state.State;
import storm.trident.state.StateFactory;

import java.io.Serializable;
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
public class OutBreakeTrendFactory implements StateFactory,Serializable{

    private static final long serialVersionUID = 1L;

    public State makeState(Map conf, IMetricsContext metrics, int partitionIndex, int numPartitions) {
        return new OutbreakeTrendState(new OutbreakeTrendBackingMap());
    }
}
