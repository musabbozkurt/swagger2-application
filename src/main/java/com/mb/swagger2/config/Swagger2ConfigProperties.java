package com.mb.swagger2.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "swagger2.services.swagger2-map")
public class Swagger2ConfigProperties {

    private Map<String, Swagger2ConfigModel> config = new HashMap<>();

}