package org.nicosoft.config.app.model;

/**
 * Api interface model
 *
 * @author nico
 * @since 2017.8.29
 */
public class ApiModel {

    private String token;
    private String serviceId;
    private String key;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
