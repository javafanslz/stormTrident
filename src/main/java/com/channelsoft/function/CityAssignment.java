package com.channelsoft.function;

import com.channelsoft.po.DiagnosisEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
public class CityAssignment extends BaseFunction {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(CityAssignment.class);
    private static Map<String,double[]> citys = new HashMap<String,double[]>();
    {
        double[] phl = {39.875325,-75.249524};
        citys.put("phl",phl);
        double[] nyc = {40.71448,-74.00598};
        citys.put("nyc",nyc);
        double[] sf = {-31.4250142,-62.0841809};
        citys.put("sf",sf);
        double[] la = {-34.05374,-118.24307};
        citys.put("la",la);
    }
    public void execute(TridentTuple tuple, TridentCollector collector) {
        DiagnosisEvent diagnosisEvent = (DiagnosisEvent) tuple.getValue(0);
        double leastDistance = Double.MAX_VALUE;
        String closestCite = "NONE";
        for(Entry<String,double[]> city:citys.entrySet()){
            double R =6371;
            double x = (city.getValue()[0]-diagnosisEvent.lng)*Math.cos((city.getValue()[0] + diagnosisEvent.lng)/2);
            double y=(city.getValue()[1] - diagnosisEvent.lat);
            double d = Math.sqrt(x * x + y * y) * R;
            if(d < leastDistance){
                leastDistance = d;
                closestCite = city.getKey();
            }
        }

        List<Object> values =  new ArrayList<Object>();
        values.add(closestCite);
        LOG.debug("Closest city to lat = ["+diagnosisEvent.lat+"],lng = ["+diagnosisEvent.lng+"] == ["+
                closestCite+"],d = ["+leastDistance+"]");
        collector.emit(values);
    }
}
