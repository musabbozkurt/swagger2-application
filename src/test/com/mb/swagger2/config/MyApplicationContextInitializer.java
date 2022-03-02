package com.mb.swagger2.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        String os = System.getProperty("os.name");
        String profile = (os.toLowerCase().startsWith("Windows")) ? "test" : "test";
        ConfigurableEnvironment ce = applicationContext.getEnvironment();
        ce.addActiveProfile(profile);
    }
}
