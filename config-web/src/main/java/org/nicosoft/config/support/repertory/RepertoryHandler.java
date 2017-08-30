package org.nicosoft.config.support.repertory;


import org.apache.commons.io.FileUtils;
import org.nicosoft.config.support.consul.ConsulService;
import org.nicosoft.config.support.exception.SysException;
import org.nicosoft.config.support.utils.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

/**
 * Git repertory handler
 *
 * @author nico
 * @since 2017.8.28
 */
@Component
public class RepertoryHandler {

    @Autowired
    Config config;
    @Autowired
    ConsulService consulService;
    @Autowired
    RepertoryService repertoryService;

    Set<String> keySet;

    Resource buildRepoDir() {
        String pathName = config.getRepo("repoDir");
        return new FileSystemResource(pathName);
    }

    /**
     * Init git repertory
     */
    void initRepo() throws SysException {
        try {
            Resource repoDir = buildRepoDir();

            if (repoDir.getFile().exists()) {
                FileUtils.deleteDirectory(repoDir.getFile());
            } else {
                repoDir.getFile().mkdirs();
            }

            repertoryService.cloneRepo(repoDir.getFile());
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

    /**
     * Find all file from repertory
     *
     * @return
     * @throws SysException
     */
    List<File> findAll() throws SysException {

        List<File> result = new ArrayList<>();

        try {
            this.initRepo();
            Resource repoDir = buildRepoDir();
            File[] files = repoDir.getFile().listFiles();
            String suffix = "properties";

            for (File file : files) {
                String name = file.getName().substring(file.getName().lastIndexOf(".") + 1);
                if (name.equalsIgnoreCase(suffix)) {
                    result.add(file);
                }
            }
        } catch (Exception e) {
            throw new SysException(e);
        }
        return result;
    }

    /**
     * Load properties
     *
     * @return
     * @throws SysException
     */
    Map<String, Properties> loadProperties() throws SysException {

        Map<String, Properties> result = new HashMap<>();

        try {
            List<File> fileList = this.findAll();
            Properties properties;
            Resource resource;

            if (fileList != null && fileList.size() > 0) {
                for (File file : fileList) {
                    resource = new FileSystemResource(file.getPath());
                    properties = new Properties();
                    properties.load(resource.getInputStream());
                    String name = file.getName();
                    int length = name.substring(name.lastIndexOf(".")).length();
                    result.put(name.substring(0, file.getName().length() - length), properties);
                }
            }
        } catch (Exception e) {
            throw new SysException(e);
        }
        return result;
    }

    /**
     * Get properties for serviceId
     *
     * @param serviceId
     * @return
     * @throws SysException
     */
    Properties getProperties(String serviceId) throws SysException {

        Properties result = new Properties();

        try {
            List<File> fileList = this.findAll();

            if (!(fileList != null && fileList.size() > 0)) {
                return result;
            }

            for (File file : fileList) {
                String name = file.getName();
                int length = name.substring(name.lastIndexOf(".")).length();
                if (serviceId.equals(name.substring(0, file.getName().length() - length))) {
                    Resource resource = new FileSystemResource(file.getPath());
                    result.load(resource.getInputStream());
                }
            }
        } catch (Exception e) {
            throw new SysException(e);
        }
        return result;
    }

    /**
     * Load repertory data in consul server
     *
     * @throws SysException
     */
    public void loadInConsul() throws SysException {
        try {
            Map<String, Properties> propertiesMap = this.loadProperties();
            keySet = new HashSet<>();

            if (propertiesMap != null && propertiesMap.size() > 0) {
                Set<String> ketSet = propertiesMap.keySet();
                for (String key : ketSet) {
                    Properties properties = propertiesMap.get(key);
                    Set<Object> propertiesKeys = properties.keySet();

                    for (Object objKeys : propertiesKeys) {
                        String cKey = key.replaceAll("-", "/") + "/" + objKeys.toString();
                        keySet.add(cKey);
                        consulService.put(cKey, properties.getProperty(objKeys.toString()));
                    }
                }
            }
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

    /**
     * reLoad repertory data in consul server
     *
     * @throws SysException
     */
    public void reLoadInConsul() throws SysException {
        try {
            if (keySet.size() > 0) {
                for (String key : keySet) {
                    consulService.delete(key);
                }
            }
            this.loadInConsul();
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

    /**
     * reLoad repertory data in consul server for serviceId
     *
     * @param serviceId
     * @throws SysException
     */
    public void reLoadInConsul(String serviceId) throws SysException {
        try {
            Properties properties = this.getProperties(serviceId);
            String prefix = serviceId.replaceAll("-", "/") + "/";
            Set<Object> keySet = properties.keySet();

            if (keySet != null && keySet.size() > 0) {
                for (Object objKey : keySet) {
                    String ckey = prefix + objKey.toString();
                    consulService.delete(ckey);
                }
            }

            this.initRepo();
            properties = this.getProperties(serviceId);
            keySet = properties.keySet();

            if (keySet != null && keySet.size() > 0) {
                for (Object objKey : keySet) {
                    String ckey = prefix + objKey.toString();
                    consulService.put(ckey, properties.getProperty(objKey.toString()));
                }
            }
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

    /**
     * Find all data by service id
     *
     * @param serviceId
     * @return
     * @throws SysException
     */
    public Map<String, Object> findAllDataByServiceId(String serviceId) throws SysException {
        Map<String, Object> result = new HashMap<>();

        try {
            Properties properties = this.getProperties(serviceId);
            Set<Object> ketSet = properties.keySet();

            for (Object objKey : ketSet) {
                result.put(objKey.toString(), properties.getProperty(objKey.toString()));
            }
            return result;
        } catch (Exception e) {
            throw new SysException(e);
        }

    }

}
