package org.nicosoft.config.support.consul;

import com.orbitz.consul.model.health.Service;
import org.nicosoft.config.support.exception.SysException;

import java.util.Map;

/**
 * Consul service
 *
 * @author nico
 * @since 2017.8.28
 */
public interface ConsulService {

    /**
     * Service register
     *
     * @throws SysException
     */
    void register() throws SysException;

    /**
     * Service deregister
     *
     * @throws SysException
     */
    void deregister() throws SysException;

    /**
     * All Service
     *
     * @throws SysException
     */
    Map<String, Service> services() throws SysException;

    /**
     * Put data
     *
     * @param key
     * @param value
     * @throws SysException
     */
    void put(String key, String value) throws SysException;

    /**
     * Get data
     *
     * @param key
     * @return
     * @throws SysException
     */
    String get(String key) throws SysException;


    /**
     * Delete data
     *
     * @param key
     * @throws SysException
     */
    void delete(String key) throws SysException;

}