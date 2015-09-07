package com.easygovm.vendingapi.service;

/**
 * Created by dwwang on 9/7/15.
 */
public interface HeartbeatService {
    public Boolean pingAndGetIfNeedUpdateConfig(String vendingId);

    Boolean setNeedUpdate(String vendingId);
}
