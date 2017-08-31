package org.nicosoft.config.support.consul.impl;

import com.orbitz.consul.model.health.Service;
import org.nicosoft.config.support.consul.ConsulService;
import org.nicosoft.config.support.exception.SysException;

import java.util.Map;

/**
 * Consul service
 *
 * @author nico
 * @since 2017.8.28
 */
public class ConsulServiceImpl implements ConsulService {

    @Override
    public void register() throws SysException {

    }

    @Override
    public void deregister() throws SysException {

    }

    @Override
    public Map<String, Service> services() throws SysException {
        return null;
    }

    @Override
    public void put(String key, String value) throws SysException {

    }

    @Override
    public String get(String key) throws SysException {
        return null;
    }

    @Override
    public void delete(String key) throws SysException {

    }
}
