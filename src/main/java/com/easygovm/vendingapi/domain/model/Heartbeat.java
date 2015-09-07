package com.easygovm.vendingapi.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by dwwang on 9/7/15.
 */
@Entity
public class Heartbeat implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String vendingId;
    @Column
    private Date lastPingTime;
    @Column
    private Boolean needUpdateConfig = Boolean.FALSE;

    public String getVendingId() {
        return vendingId;
    }

    public void setVendingId(String vendingId) {
        this.vendingId = vendingId;
    }

    public Date getLastPingTime() {
        return lastPingTime;
    }

    public void setLastPingTime(Date lastPingTime) {
        this.lastPingTime = lastPingTime;
    }

    public Boolean getNeedUpdateConfig() {
        return needUpdateConfig;
    }

    public void setNeedUpdateConfig(Boolean needUpdateConfig) {
        this.needUpdateConfig = needUpdateConfig;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
