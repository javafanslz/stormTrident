package com.channelsoft.function;


import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class OutbreakeDetector extends BaseFunction implements Serializable{
    private static final long serialVersionUID = 1L;
    public static final int THRESHOLD = 10000;

    public void execute(TridentTuple tuple, TridentCollector collector) {
        String key = (String) tuple.getValue(0);
        Long count =(Long) tuple.getValue(1);
        if(count > THRESHOLD){
            List<Object> values = new ArrayList<Object>();
            values.add("outbreake detected for ["+key+"]");
            collector.emit(values);
        }
    }
}
