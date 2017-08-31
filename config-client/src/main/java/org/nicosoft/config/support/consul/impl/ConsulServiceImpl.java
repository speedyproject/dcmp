package org.nicosoft.config.support.consul.impl;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;
import com.orbitz.consul.model.health.Service;
import org.nicosoft.config.support.consul.ConsulService;
import org.nicosoft.config.support.exception.SysException;
import org.nicosoft.config.support.utils.Configurer;
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

    /**
     * Build consul
     *
     * @return
     */
    Consul buildConsul() {
        Consul consul = Consul.builder().withHostAndPort(HostAndPort.fromString(Configurer.host)).build();
        return consul;
    }

    @Override
    public void register() throws SysException {
        try {
            AgentClient agentClient = buildConsul().agentClient();
            agentClient.register(Configurer.port, URI.create(Configurer.check).toURL(), Configurer.ttl,
                    Configurer.serviceName, Configurer.serviceId);
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

    @Override
    public void deregister() throws SysException {
        try {
            AgentClient agentClient = buildConsul().agentClient();
            agentClient.deregister(Configurer.serviceId);
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

    @Override
    public Map<String, Service> services() throws SysException {
        try {
            Map<String, Service> serviceMap = buildConsul().agentClient().getServices();
            if (serviceMap != null && serviceMap.size() > 0) {
                if (serviceMap.containsKey("consul")) {
                    serviceMap.remove("consul");
                }
            }
            return serviceMap;
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

    @Override
    public void put(String key, String value) throws SysException {
        try {
            KeyValueClient keyValueClient = buildConsul().keyValueClient();
            keyValueClient.putValue(key, value);
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

    @Override
    public String get(String key) throws SysException {
        try {
            KeyValueClient keyValueClient = buildConsul().keyValueClient();
            return keyValueClient.getValueAsString(key).get();
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

    @Override
    public void delete(String key) throws SysException {
        try {
            KeyValueClient keyValueClient = buildConsul().keyValueClient();
            keyValueClient.deleteKey(key);
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

}
