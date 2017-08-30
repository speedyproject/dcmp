package org.nicosoft.config.support.utils;

import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Config support
 *
 * @author nico
 * @since 2017.8.28
 */
@Component
public class Config {

    private Map<String, Object> dataMap;
    private Map<String, Object> consulMap;
    private Map<String, Object> repoMap;

    private String token;

    /**
     * Get token
     *
     * @return
     */
    public String getToken() {
        this.token = dataMap.get("token").toString();
        return token;
    }

    @SuppressWarnings("unchecked")
    public Config() {
        YamlMapFactoryBean yamlMapFactoryBean = new YamlMapFactoryBean();
        yamlMapFactoryBean.setResources(new ClassPathResource("config.yml"));

        this.dataMap = (Map<String, Object>) yamlMapFactoryBean.getObject().get("config");
        this.consulMap = (Map<String, Object>) this.dataMap.get("consul");
        this.repoMap = (Map<String, Object>) this.dataMap.get("repo");
    }

    /**
     * Get consul config
     *
     * @param key
     * @return
     */
    public String getConsul(String key) {
        return this.consulMap.get(key).toString();
    }

    /**
     * Get git repo config
     *
     * @param key
     * @return
     */
    public String getRepo(String key) {
        return this.repoMap.get(key).toString();
    }

}
