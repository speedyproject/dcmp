package org.nicosoft.config.support.spring.impl;

import org.nicosoft.config.support.exception.SysException;
import org.nicosoft.config.support.spring.ConfigurerBeanHandler;
import org.nicosoft.config.support.utils.Configurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Properties;

/**
 * Configurer bean Handler
 *
 * @author nico
 * @since 2017.8.31
 */
@Component
public class ConfigurerBeanHandlerImpl implements ConfigurerBeanHandler {

    @Override
    public Properties[] findProperties(String dirPath) throws SysException {

        try {
            Resource resource = new FileSystemResource(Configurer.filePath);
            File[] files = resource.getFile().listFiles();
            Properties[] propertiesArray = new Properties[]{};
            Properties properties;

            if (files == null || files.length == 0) {
                return null;
            }

            for (int i = 0; i < files.length; i++) {
                resource = new FileSystemResource(files[i].getPath());
                properties = new Properties();
                properties.load(resource.getInputStream());
                propertiesArray[i] = properties;
            }

            return propertiesArray;
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

    @Override
    public void refurbish(String dirPath) throws SysException {

    }

}
