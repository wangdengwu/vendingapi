package com.easygovm.vendingapi.web;

import com.easygovm.vendingapi.Application;
import com.easygovm.vendingapi.domain.model.Heartbeat;
import com.easygovm.vendingapi.domain.repository.HeartbeatRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebAppConfiguration
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("/data/heartbeat.xml")
public class HeartbeatControllerTest {

    public static final String PING_VENDING_ID = "123456";
    public static final String Update_VENDING_ID = "654321";
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private HeartbeatRepository heartbeatRepository;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    @Transactional
    public void testPing() throws Exception {
        Heartbeat heartbeat = heartbeatRepository.findByVendingId(PING_VENDING_ID);
        Date old = heartbeat.getLastPingTime();
        this.mvc.perform(get("/api/ping/123456")).andExpect(status().isOk())
                .andExpect(jsonPath("$.needUpdateConfig", is(Boolean.FALSE)));
        heartbeat = heartbeatRepository.findByVendingId(PING_VENDING_ID);
        assertThat(heartbeat.getLastPingTime(), not(old));
    }

    @Test
    @Transactional
    public void testSetNeedUpdate() throws Exception {
        this.mvc.perform(get("/api/ping/654321/true")).andExpect(status().isOk())
                .andExpect(content().string(is("true")));
        Heartbeat heartbeat = heartbeatRepository.findByVendingId(Update_VENDING_ID);
        assertThat(heartbeat.getNeedUpdateConfig(), is(Boolean.TRUE));
    }
}