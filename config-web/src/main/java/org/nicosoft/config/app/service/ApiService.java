package org.nicosoft.config.app.service;

import org.nicosoft.config.app.model.ApiModel;
import org.nicosoft.config.support.exception.SysException;

import java.util.Map;

/**
 * Api interface service
 *
 * @author nico
 * @since 2017.8.29
 */
public interface ApiService {

    /**
     * Refurbish KV data
     *
     * @param apiModel
     * @throws SysException
     */
    void refurbish(ApiModel apiModel) throws SysException;

    /**
     * Find data by serviceId
     *
     * @param apiModel
     * @return
     * @throws SysException
     */
    String findData(ApiModel apiModel) throws SysException;

    /**
     * Find All data
     *
     * @param apiModel
     * @return
     * @throws SysException
     */
    Map<String, Object> findAll(ApiModel apiModel) throws SysException;

}
