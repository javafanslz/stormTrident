package com.channelsoft.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.state.map.IBackingMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
public class OutbreakeTrendBackingMap implements IBackingMap<Long> {
    private static final Logger LOG = LoggerFactory.getLogger(OutbreakeTrendBackingMap.class);
    Map<String,Long> storage = new ConcurrentHashMap<String,Long>();
    public List<Long> multiGet(List<List<Object>> keys) {
        List<Long> values = new ArrayList<Long>();
        for(List<Object> key:keys){
            Long value = storage.get(key.get(0));
            if(value == null){
                values.add(new Long(0));
            }else{
                values.add(value);
            }
        }
        return values;
    }

    public void multiPut(List<List<Object>> keys, List<Long> vals) {
        for(int i=0;i< keys.size();i++){
            LOG.info("Persisting ["+keys.get(i).get(0)+"] == ["+vals.get(i)+"]");
            storage.put((String)keys.get(i).get(0),vals.get(i));
        }
    }
}
