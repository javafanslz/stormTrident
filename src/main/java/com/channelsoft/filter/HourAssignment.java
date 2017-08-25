package com.channelsoft.filter;



import com.channelsoft.po.DiagnosisEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class HourAssignment extends BaseFunction implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(HourAssignment.class);

    public void execute(TridentTuple tuple, TridentCollector collector) {
        DiagnosisEvent diagnosisEvent = (DiagnosisEvent)tuple.getValue(0);
        String city = (String) tuple.getValue(1);
        long timestamp =diagnosisEvent.time;
        long hourSinceEpoch = timestamp / 1000 / 60 / 60;//将时间戳转换为小时

        LOG.debug("Key = ["+city+ ":"+hourSinceEpoch +"]");
        String  key = city +":" +diagnosisEvent.diagnosisCode+":"+hourSinceEpoch;
        List<Object> values = new ArrayList<Object>();
        values.add(hourSinceEpoch);
        values.add(key);
        collector.emit(values);

    }
}
