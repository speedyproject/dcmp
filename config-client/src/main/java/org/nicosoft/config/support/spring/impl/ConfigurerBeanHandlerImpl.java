package org.nicosoft.config.support.spring.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.nicosoft.config.support.consul.ConsulService;
import org.nicosoft.config.support.exception.SysException;
import org.nicosoft.config.support.spring.ConfigurerBeanHandler;
import org.nicosoft.config.support.utils.Configurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.util.Properties;
import java.util.Set;

/**
 * Configurer bean Handler
 *
 * @author nico
 * @since 2017.8.31
 */
@Component
public class ConfigurerBeanHandlerImpl implements ConfigurerBeanHandler {

    @Autowired
    ConsulService consulService;

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

            String suffix = "properties";

            for (int i = 0; i < files.length; i++) {
                String name = files[i].getName().substring(files[i].getName().lastIndexOf(".") + 1);

                if (!suffix.equalsIgnoreCase(name)) {
                    continue;
                }

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
        this.buildProperties(dirPath);
    }

    @Override
    public void buildProperties(String dirPath) throws SysException {
        try {
            Properties properties = new Properties();
            String cKey = Configurer.serviceId + "-" + Configurer.profile;
            Set<String> ckeySet = new Gson().fromJson(consulService.get(cKey), new TypeToken<Set<String>>() {
            }.getType());

            for (String key : ckeySet) {
                String value = consulService.get(key);
                properties.put(key, value);
            }

            if (StringUtils.isBlank(dirPath)) {
                throw new SysException("dirPath can't be null");
            }

            Resource resource = new FileSystemResource(dirPath);

            if (resource.getFile().exists()) {
                FileUtils.deleteDirectory(resource.getFile());
            } else {
                resource.getFile().mkdirs();
            }

            dirPath = dirPath + cKey + ".properties";
            properties.store(new FileWriter(dirPath), "Service config");
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

}
