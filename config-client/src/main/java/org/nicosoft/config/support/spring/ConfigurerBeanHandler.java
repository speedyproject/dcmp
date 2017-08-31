package org.nicosoft.config.support.spring;

import org.nicosoft.config.support.exception.SysException;

import java.util.Properties;

/**
 * Configurer bean Handler
 *
 * @author nico
 * @since 2017.8.31
 */
public interface ConfigurerBeanHandler {

    /**
     * Find all properties in dirPath
     *
     * @param dirPath
     * @return
     * @throws SysException
     */
    Properties[] findProperties(String dirPath) throws SysException;

    /**
     * refurbish all properties in dirPath
     *
     * @param dirPath
     * @throws SysException
     */
    void refurbish(String dirPath) throws SysException;


    /**
     * Build properties in dirPath
     *
     * @param dirPath
     * @throws SysException
     */
    void buildProperties(String dirPath) throws SysException;


}
