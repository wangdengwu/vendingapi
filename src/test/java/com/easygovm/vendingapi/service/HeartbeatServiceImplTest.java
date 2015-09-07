package com.easygovm.vendingapi.service;

import com.easygovm.vendingapi.Application;
import com.easygovm.vendingapi.domain.model.Heartbeat;
import com.easygovm.vendingapi.domain.repository.HeartbeatRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebAppConfiguration
public class HeartbeatServiceImplTest {

    @InjectMocks
    private HeartbeatServiceImpl heartbeatService;

    @Mock
    private HeartbeatRepository heartbeatRepository;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testPingAndGetIfNeedUpdateConfig() throws Exception {
        Mockito.when(heartbeatRepository.findByVendingId(anyString())).thenReturn(null);
        Mockito.when(heartbeatRepository.save(any(Heartbeat.class))).thenReturn(null);
        assertThat(Boolean.FALSE,is(heartbeatService.pingAndGetIfNeedUpdateConfig(anyString())));
    }

    @Test
    public void testSetNeedUpdate() throws Exception {
        Heartbeat heartbeat = new Heartbeat();
        Mockito.doReturn(heartbeat).when(heartbeatRepository).findByVendingId(anyString());
        Mockito.when(heartbeatRepository.save(any(Heartbeat.class))).thenReturn(null);
        assertThat(Boolean.TRUE,is(heartbeatService.setNeedUpdate(anyString())));
    }
}