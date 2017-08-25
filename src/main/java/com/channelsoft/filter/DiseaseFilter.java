package com.channelsoft.filter;

import com.channelsoft.po.DiagnosisEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.operation.BaseFilter;
import storm.trident.tuple.TridentTuple;

import java.io.Serializable;

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
public class DiseaseFilter extends BaseFilter implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final Logger LOG= LoggerFactory.getLogger(DiseaseFilter.class);

    public boolean isKeep(TridentTuple tuple) {
        DiagnosisEvent diagnosisEvent =(DiagnosisEvent)tuple.getValue(0);
        Integer code = Integer.parseInt(diagnosisEvent.diagnosisCode);
        if(code.intValue() <= 322){
            LOG.debug("Emitting disease ["+diagnosisEvent.diagnosisCode+"]");
            return true;
        }else{
            LOG.debug("Filtering disease["+diagnosisEvent.diagnosisCode+"]");
            return false;
        }

    }
}
