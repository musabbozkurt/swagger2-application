package com.mb.swagger2.api.controller;

import com.google.gson.Gson;
import com.mb.swagger2.config.Swagger2ConfigModel;
import com.mb.swagger2.config.Swagger2ConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class Swagger2ControllerTest {

    @Mock
    private Swagger2ConfigProperties swagger2ConfigProperties;

    @Autowired
    private Environment environment;

    private MockMvc mockMvc;

    @InjectMocks
    private Swagger2Controller swagger2Controller;

    @Before
    public void setUp() {
        Map<String, Swagger2ConfigModel> config = swagger2ConfigProperties.getConfig();
        if (config == null || config.size() == 0) {
            config = new HashMap<>();
            Swagger2ConfigModel pspQrPaymentConfigModel = new Swagger2ConfigModel();
            pspQrPaymentConfigModel.setUrl(environment.getProperty("swagger2.services.swagger2-map.config.swagger2-config-1.url"));
            pspQrPaymentConfigModel.setAppUrl(environment.getProperty("swagger2.services.swagger2-map.config.swagger2-config-1.app-url"));
            pspQrPaymentConfigModel.setAppName(environment.getProperty("swagger2.services.swagger2-map.config.swagger2-config-1.app-name"));

            Swagger2ConfigModel tencentQrPaymentConfigModel = new Swagger2ConfigModel();
            tencentQrPaymentConfigModel.setUrl(environment.getProperty("swagger2.services.swagger2-map.config.swagger2-config-2.url"));
            tencentQrPaymentConfigModel.setAppUrl(environment.getProperty("swagger2.services.swagger2-map.config.swagger2-config-2.app-url"));
            tencentQrPaymentConfigModel.setAppName(environment.getProperty("swagger2.services.swagger2-map.config.swagger2-config-2.app-name"));
            config.put("swagger2-config-1", pspQrPaymentConfigModel);
            config.put("swagger2-config-2", tencentQrPaymentConfigModel);
            swagger2ConfigProperties.setConfig(config);
        }
        mockMvc = MockMvcBuilders
                .standaloneSetup(swagger2Controller, swagger2ConfigProperties)
                .build();
    }

    /**
     * Method: getUserConfigProperties()
     */
    @Test
    public void getUserConfigProperties() throws Exception {
        Gson gson = new Gson();
        String configProperties = gson.toJson(swagger2ConfigProperties.getConfig());
        mockMvc.perform(post("/api/user/config/")
                        .content(configProperties).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

} 
