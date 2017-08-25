package com.channelsoft.state;

import storm.trident.state.map.IBackingMap;
import storm.trident.state.map.NonTransactionalMap;

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
public class OutbreakeTrendState extends NonTransactionalMap<Long> {

    public OutbreakeTrendState(OutbreakeTrendBackingMap outbreakeTrendBackingMap) {
        super(outbreakeTrendBackingMap);
    }
}
