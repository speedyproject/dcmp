package org.nicosoft.config.support.consul.impl;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;
import com.orbitz.consul.model.health.Service;
import org.nicosoft.config.support.consul.ConsulService;
import org.nicosoft.config.support.exception.SysException;
import org.nicosoft.config.support.utils.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Map;


/**
 * Consul service
 *
 * @author nico
 * @since 2017.8.28
 */
@Component
public class ConsulServiceImpl implements ConsulService {

    @Autowired
    Config config;

    /**
     * Build consul
     *
     * @return
     */
    Consul buildConsul() {
        String host = config.getConsul("host");
        Consul consul = Consul.builder().withHostAndPort(HostAndPort.fromString(host)).build();
        return consul;
    }


    @Override
    public void register() throws SysException {

        AgentClient agentClient = buildConsul().agentClient();

        try {
            String check = config.getConsul("check");
            String serviceId = config.getConsul("serviceId");
            String serviceName = config.getConsul("serviceName");
            int port = Integer.parseInt(config.getConsul("port"));
            long ttl = Long.parseLong(config.getConsul("ttl"));
            agentClient.register(port, URI.create(check).toURL(), ttl, serviceName, serviceId);
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

    @Override
    public void deregister() throws SysException {

        AgentClient agentClient = buildConsul().agentClient();

        try {
            agentClient.deregister(config.getConsul("serviceId"));
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

    @Override
    public Map<String, Service> services() throws SysException {

        Map<String, Service> serviceMap = buildConsul().agentClient().getServices();

        if (serviceMap != null && serviceMap.size() > 0) {
            if (serviceMap.containsKey("consul")) {
                serviceMap.remove("consul");
            }
        }

        return serviceMap;
    }

    @Override
    public void put(String key, String value) throws SysException {

        KeyValueClient keyValueClient = buildConsul().keyValueClient();

        try {
            keyValueClient.putValue(key, value);
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

    @Override
    public String get(String key) throws SysException {

        KeyValueClient keyValueClient = buildConsul().keyValueClient();

        try {
            return keyValueClient.getValueAsString(key).get();
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

    @Override
    public void delete(String key) throws SysException {

        KeyValueClient keyValueClient = buildConsul().keyValueClient();

        try {
            keyValueClient.deleteKey(key);
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

}
