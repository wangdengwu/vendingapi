package com.easygovm.vendingapi.web;

import com.easygovm.vendingapi.domain.view.HeartbeatView;
import com.easygovm.vendingapi.service.HeartbeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dwwang on 9/7/15.
 */
@RestController
public class HeartbeatController {

    @Autowired
    private HeartbeatService heartbeatService;

    @RequestMapping(value = "/api/ping/{vendingId}",method = RequestMethod.GET)
    public HeartbeatView ping(@PathVariable String vendingId) {
        Boolean needUpdateConfig = heartbeatService.pingAndGetIfNeedUpdateConfig(vendingId);
        return new HeartbeatView(needUpdateConfig);
    }
}
