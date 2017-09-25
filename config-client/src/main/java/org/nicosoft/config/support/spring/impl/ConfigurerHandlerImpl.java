package org.nicosoft.config.support.spring.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.nicosoft.config.bean.ConfigurerBean;
import org.nicosoft.config.support.consul.ConsulService;
import org.nicosoft.config.support.consul.impl.ConsulServiceImpl;
import org.nicosoft.config.support.exception.SysException;
import org.nicosoft.config.support.spring.ConfigurerHandler;
import org.nicosoft.config.support.spring.SpringBeanFactory;
import org.nicosoft.config.support.utils.Configurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.util.Properties;

/**
 * Configurer bean Handler
 *
 * @author nico
 * @since 2017.8.31
 */
@Component
public class ConfigurerHandlerImpl implements ConfigurerHandler {

    @Autowired
    SpringBeanFactory springBeanFactory;

    @Override
    public Resource[] findProperties() throws SysException {
        try {
            Resource resource = new FileSystemResource(Configurer.filePath);
            File[] files = resource.getFile().listFiles();

            if (files == null || files.length == 0) {
                return null;
            }

            Resource[] resourceArray = new Resource[files.length];
            String suffix = "properties";

            for (int i = 0; i < files.length; i++) {
                String name = files[i].getName().substring(files[i].getName().lastIndexOf(".") + 1);

                if (!suffix.equalsIgnoreCase(name)) {
                    continue;
                }

                resourceArray[i] = new FileSystemResource(files[i].getPath());
            }
            return resourceArray;
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

    @Override
    public void refurbish() throws SysException {
        try{
            springBeanFactory.reloadBean(ConfigurerBean.class, "load", "destroy");
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

    @Override
    public void buildProperties() throws SysException {
        try {
            ConsulService consulService = new ConsulServiceImpl();
            Properties properties = new Properties();
            String cKey = Configurer.serviceId + "-" + Configurer.profile;
            String prefix = cKey.replaceAll("-", "/") + "/";
            String[] ckeySet = new Gson().fromJson(consulService.get("service-keys/" + cKey),
                    new TypeToken<String[]>() {
                    }.getType());

            for (String key : ckeySet) {
                String value = consulService.get(prefix + key);
                properties.put(key, value);
            }

            Resource resource = new FileSystemResource(Configurer.filePath);

            if (resource.getFile().exists()) {
                FileUtils.deleteDirectory(resource.getFile());
            }

            resource.getFile().mkdirs();

            String filePath = Configurer.filePath + cKey + ".properties";
            properties.store(new FileWriter(filePath), "Service config build for DCMP");
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

}