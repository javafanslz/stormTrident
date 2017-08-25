package com.channelsoft.po;

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
public class DiagnosisEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    public double lat;//纬度
    public double lng;//经度
    public long time;//时间戳
    public String diagnosisCode;//ICD-9-CM码

    public DiagnosisEvent(double lat, double lng, long time, String diagnosisCode) {
        this.lat = lat;
        this.lng = lng;
        this.time = time;
        this.diagnosisCode = diagnosisCode;
    }
}
