package org.nicosoft.config.bean;

import org.nicosoft.config.support.spring.SpringBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfigurer {

    @Bean(initMethod = "load", destroyMethod = "destroy")
    public ConfigurerBean configurerBean() {
        ConfigurerBean configurerBean = new ConfigurerBean();
        return configurerBean;
    }

    @Bean
    public SpringBeanFactory springBeanFactory(){
        SpringBeanFactory springBeanFactory = new SpringBeanFactory();
        return springBeanFactory;
    }

}
