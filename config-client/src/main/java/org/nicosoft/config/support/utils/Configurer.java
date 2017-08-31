package org.nicosoft.config.support.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.util.Map;

/**
 * Configurer utils
 *
 * @author nico
 * @since 2017.8.31
 */
public class Configurer {

    public static String profile;
    public static String token;
    public static String filePath;

    public static String host;
    public static long ttl;
    public static int port;
    public static String check;
    public static String serviceId;
    public static String serviceName;


    static {
        try {
            Yaml yaml = new Yaml();

            @SuppressWarnings("unchecked")
            Map<String, Object> dataMap = yaml.loadAs(new FileInputStream(Configurer.class.getResource(
                    "/config.yml").getPath()), Map.class);

            @SuppressWarnings("unchecked")
            Map<String, Object> configMap = (Map<String, Object>) dataMap.get("config");

            profile = configMap.get("profile").toString();
            token = configMap.get("token").toString();
            filePath = configMap.get("filePath").toString();

            @SuppressWarnings("unchecked")
            Map<String, Object> consulMap = (Map<String, Object>) configMap.get("consul");

            host = consulMap.get("host").toString();
            ttl = Long.parseLong(consulMap.get("ttl").toString());
            port = Integer.parseInt(consulMap.get("port").toString());
            check = consulMap.get("check").toString();
            serviceId = consulMap.get("serviceId").toString();
            serviceName = consulMap.get("serviceName").toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
