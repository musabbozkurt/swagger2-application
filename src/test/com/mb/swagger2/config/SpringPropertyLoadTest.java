package com.mb.swagger2.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(initializers = MyApplicationContextInitializer.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class SpringPropertyLoadTest {

    @Autowired
    private Environment env;

    @Test
    public void shouldLoadDefaultProps() {
        assertThat(env.getProperty("swagger2.services.swagger2-map.config.swagger2-config-1.url")).isEqualTo("http://localhost:8082/v3/api-docs");
        assertThat(env.getProperty("swagger2.services.swagger2-map.config.swagger2-config-1.app-url")).isEqualTo("http://localhost:8082/api");
        assertThat(env.getProperty("swagger2.services.swagger2-map.config.swagger2-config-1.app-name")).isEqualTo("swagger2-application");
        assertThat(env.getProperty("swagger2.services.swagger2-map.config.swagger2-config-2.url")).isEqualTo("http://localhost:8082/swagger-ui/index.html");
        assertThat(env.getProperty("swagger2.services.swagger2-map.config.swagger2-config-2.app-url")).isEqualTo("http://localhost:8082/api");
        assertThat(env.getProperty("swagger2.services.swagger2-map.config.swagger2-config-2.app-name")).isEqualTo("swagger2-application");
    }

    @Test
    public void shouldOverrideDefaultPropWithTestProp() {
        assertThat(env.getProperty("property")).isEqualTo("test_property");
    }

}
