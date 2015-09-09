package com.easygovm.vendingapi.domain.repository;

import com.easygovm.vendingapi.domain.model.Heartbeat;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dwwang on 9/7/15.
 */
public interface HeartbeatRepository extends CrudRepository<Heartbeat, Long> {
    public Heartbeat findByVendingId(String vendingId);
}
