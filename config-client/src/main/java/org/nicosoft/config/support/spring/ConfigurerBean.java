package org.nicosoft.config.support.spring;

import org.nicosoft.config.support.utils.Configurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;


/**
 * Expands spring PropertyPlaceholderConfigurer
 *
 * @author nico
 * @since 2017.8.31
 */
public class ConfigurerBean extends PropertyPlaceholderConfigurer {

    @Autowired
    ConfigurerBeanHandler configurerBeanHandler;

    /**
     * Init bean
     */
    public void init() {
        try {
            this.setPropertiesArray(configurerBeanHandler.findProperties(Configurer.filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
