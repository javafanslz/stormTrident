package com.channelsoft.topology;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.tuple.Fields;
import com.channelsoft.filter.DiseaseFilter;
import com.channelsoft.filter.HourAssignment;
import com.channelsoft.function.DispatcherAlert;
import com.channelsoft.function.OutbreakeDetector;
import com.channelsoft.spout.DiagnosisEventSpot;
import com.channelsoft.stateFactory.OutBreakeTrendFactory;
import storm.trident.Stream;
import storm.trident.TridentTopology;
import storm.trident.operation.builtin.Count;

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
public class OutbreakDetectionTopology {

    public static StormTopology buildTopology(){
        TridentTopology tridentTopology =new TridentTopology();
        DiagnosisEventSpot spout =new DiagnosisEventSpot();
        Stream inputStream = tridentTopology.newStream("event",spout);
        inputStream.each(new Fields("event"),new DiseaseFilter())
                .each(new Fields("event","city"),new HourAssignment(),new Fields("hour","cityDiseaseHour"))
                .groupBy(new Fields("cityDisease"))
                .persistentAggregate(new OutBreakeTrendFactory(),new Count(),new Fields("count"))
                .newValuesStream()
                .each(new Fields("cityDiseaseHour","count"),new OutbreakeDetector(),new Fields("alert"))
                .each(new Fields("alert"),new DispatcherAlert(),new Fields());
        return tridentTopology.build();
    }

    public static void main(String[] args) throws InterruptedException {
        Config config= new Config();
        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("cdc",config,buildTopology());
        Thread.sleep(2000000);
        localCluster.shutdown();
    }
}
