package org.nicosoft.config.support.spring;

import org.nicosoft.config.support.consul.ConsulService;
import org.nicosoft.config.support.consul.impl.ConsulServiceImpl;
import org.nicosoft.config.support.spring.impl.ConfigurerHandlerImpl;
import org.nicosoft.config.support.utils.Logger;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * PropertyPlaceholderConfigurer bean
 *
 * @author nico
 * @since 2017.8.31
 */
public class ConfigurerBean extends PropertyPlaceholderConfigurer {

    /**
     * Don't use @Autowired.
     */
    ConfigurerHandler configurerHandler;
    ConsulService consulService;

    /**
     * Init bean
     */
    public void load() {
        configurerHandler = new ConfigurerHandlerImpl();
        consulService = new ConsulServiceImpl();

        consulService.register();
        Logger.info("Service register success.");

        configurerHandler.buildProperties();
        Logger.info("Build properties file success");

        this.setLocations(configurerHandler.findProperties());
        Logger.info("ConfigurerBean load success");
    }

    /**
     * Destroy bean
     */
    public void destroy() {
        consulService.deregister();
        Logger.info("Service deregister success.");
    }
}
