package com.channelsoft.emitter;


import com.channelsoft.po.DiagnosisEvent;
import storm.trident.operation.TridentCollector;
import storm.trident.spout.ITridentSpout.Emitter;
import storm.trident.topology.TransactionAttempt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
public class DiagnosisiEventEmitter implements Emitter,Serializable {

    private static final long serialVersionUID = 1L;

    AtomicInteger successfulTransactions = new AtomicInteger(0);

    public void emitBatch(TransactionAttempt tx, Object coordinatorMeta, TridentCollector collector) {
        for(int i = 0;i < 1000;i++){
            List<Object> event = new ArrayList<Object>();
            double lat = new Double(-30 + (int) (Math.random() * 75));//纬度
            double lng = new Double(-120 + (int) (Math.random() * 70));//经度
            long time = System.currentTimeMillis();
            String diag = new Integer(320 + (int) (Math.random() * 7)).toString();
            DiagnosisEvent diagnosisEvent =new DiagnosisEvent(lat,lng,time,diag);
            event.add(diagnosisEvent);
            collector.emit(event);
        }
    }

    public void success(TransactionAttempt tx) {

    }

    public void close() {

    }
}
