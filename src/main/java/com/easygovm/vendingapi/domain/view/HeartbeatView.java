package com.easygovm.vendingapi.domain.view;

/**
 * Created by dwwang on 9/7/15.
 */
public class HeartbeatView {
    private Boolean needUpdateConfig;

    public HeartbeatView(Boolean needUpdateConfig) {
        this.needUpdateConfig = needUpdateConfig;
    }
    public Boolean getNeedUpdateConfig() {
        return needUpdateConfig;
    }
}
