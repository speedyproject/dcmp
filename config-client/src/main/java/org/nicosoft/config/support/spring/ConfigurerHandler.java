package org.nicosoft.config.support.spring;

import org.nicosoft.config.support.exception.SysException;
import org.springframework.core.io.Resource;


/**
 * Configurer bean Handler
 *
 * @author nico
 * @since 2017.8.31
 */
public interface ConfigurerHandler {

    /**
     * Find all properties in dirPath
     *
     * @return
     * @throws SysException
     */
    Resource[] findProperties() throws SysException;

    /**
     * refurbish all properties in dirPath
     *
     * @throws SysException
     */
    void refurbish() throws SysException;


    /**
     * Build properties in dirPath
     *
     * @throws SysException
     */
    void buildProperties() throws SysException;

}
