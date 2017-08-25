package com.channelsoft.coordinator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.spout.ITridentSpout.BatchCoordinator;

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
public class DefaultCoodinator implements BatchCoordinator<Long>,Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG  = LoggerFactory.getLogger(DefaultCoodinator.class);


    public Long initializeTransaction(long txid, Long prevMetadata, Long currMetadata) {
        LOG.info("初始化Transaction["+txid+"]");
        return null;
    }


    public void success(long txid) {
        LOG.info("success transaction ["+txid+"]");
    }

    public boolean isReady(long txid) {
        return true;
    }

    public void close() {

    }
}
