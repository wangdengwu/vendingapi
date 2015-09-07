package com.easygovm.vendingapi.web;

import com.easygovm.vendingapi.Application;
import com.easygovm.vendingapi.service.HeartbeatService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebAppConfiguration
public class HeartbeatControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @InjectMocks
    private HeartbeatController heartbeatController;

    @Mock
    private HeartbeatService heartbeatService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testPing() throws Exception {
        Mockito.when(heartbeatService.pingAndGetIfNeedUpdateConfig(anyString())).thenReturn(Boolean.FALSE);
        this.mvc.perform(get("/api/ping/asdaada")).andExpect(status().isOk())
                .andExpect(jsonPath("$.needUpdateConfig", is(Boolean.FALSE)));
    }

    @Test
    public void testSetNeedUpdate() throws Exception {
        Mockito.when(heartbeatService.setNeedUpdate(anyString())).thenReturn(Boolean.TRUE);
        this.mvc.perform(get("/api/ping/asdaada/true")).andExpect(status().isOk())
                .andExpect(content().string(is("true")));
    }
}