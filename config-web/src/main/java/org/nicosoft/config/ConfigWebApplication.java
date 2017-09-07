package org.nicosoft.config;

import org.nicosoft.config.support.consul.ConsulBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@EnableScheduling
@SpringBootApplication
@ComponentScan("org.nicosoft.config")
public class ConfigWebApplication {

    @Bean(initMethod = "init",destroyMethod = "destroy")
    public ConsulBean consulBean() {
        return new ConsulBean();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigWebApplication.class, args);
    }

}
