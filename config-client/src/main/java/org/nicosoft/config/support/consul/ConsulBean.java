package org.nicosoft.config.support.consul;

import org.nicosoft.config.support.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Consul spring register bean, only spring use.
 *
 * @author nico
 * @since 2017.8.21
 */
public class ConsulBean {

    @Autowired
    ConsulService consulService;

    /**
     * Spring bean init method
     */
    public void init() {
        consulService.register();
        Logger.info("Service register success.");
    }

    /**
     * Spring bean destroy method
     */
    public void destroy() {
        consulService.deregister();
        Logger.info("Service deregister success.");
    }

}
