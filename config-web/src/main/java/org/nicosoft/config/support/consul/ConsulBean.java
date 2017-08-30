package org.nicosoft.config.support.consul;

import org.nicosoft.config.support.repertory.RepertoryHandler;
import org.nicosoft.config.support.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Consul spring register bean.
 *
 * @author nico
 * @since 2017.8.28
 */
public class ConsulBean {

    @Autowired
    ConsulService consulService;
    @Autowired
    RepertoryHandler repertoryHandler;

    public void init() {
        consulService.register();
        Logger.info("Service register success.");
        repertoryHandler.loadInConsul();
        Logger.info("Load repertory data in consul success.");
    }

    public void destroy() {
        consulService.deregister();
        Logger.info("Service deregister success.");
    }

}
