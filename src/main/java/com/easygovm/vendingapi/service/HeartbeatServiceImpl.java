package com.easygovm.vendingapi.service;

import com.easygovm.vendingapi.domain.model.Heartbeat;
import com.easygovm.vendingapi.domain.repository.HeartbeatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by dwwang on 9/7/15.
 */
@Service
@Transactional
public class HeartbeatServiceImpl implements HeartbeatService{
    private static final Logger logger = LoggerFactory.getLogger(HeartbeatServiceImpl.class);

    @Autowired
    private HeartbeatRepository heartbeatRepository;

    @Override
    public Boolean pingAndGetIfNeedUpdateConfig(String vendingId) {
        Heartbeat heartbeat = heartbeatRepository.findByVendingId(vendingId);
        if (null == heartbeat){
            heartbeat = new Heartbeat();
        }
        Boolean needUpdateConfig = heartbeat.getNeedUpdateConfig();
        if (needUpdateConfig){//just first time return true,request next time will be false
            heartbeat.setNeedUpdateConfig(Boolean.FALSE);
        }
        heartbeat.setLastPingTime(new Date());
        heartbeat.setVendingId(vendingId);
        heartbeatRepository.save(heartbeat);
        logger.info("heartbeat->lastPingTime:"+heartbeat.getLastPingTime());
        return needUpdateConfig;
    }

    @Override
    public Boolean setNeedUpdate(String vendingId) {
        Boolean success = Boolean.FALSE;
        Heartbeat heartbeat = heartbeatRepository.findByVendingId(vendingId);
        if(heartbeat != null){
            heartbeat.setNeedUpdateConfig(Boolean.TRUE);
            heartbeatRepository.save(heartbeat);
            success = Boolean.TRUE;
        }
        return success;
    }
}
