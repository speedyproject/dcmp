package org.nicosoft.config.app.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.nicosoft.config.app.model.ApiModel;
import org.nicosoft.config.app.service.ApiService;
import org.nicosoft.config.support.consul.ConsulService;
import org.nicosoft.config.support.exception.SysException;
import org.nicosoft.config.support.repertory.RepertoryHandler;
import org.nicosoft.config.support.utils.Config;
import org.nicosoft.config.support.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * Api interface service
 *
 * @author nico
 * @since 2017.8.29
 */
@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    Config config;
    @Autowired
    ConsulService consulService;
    @Autowired
    RepertoryHandler repertoryHandler;

    /**
     * Check token
     *
     * @param apiModel
     */
    void checkToken(ApiModel apiModel) {

        String token = config.getToken();

        if (StringUtils.isBlank(token)) {
            return;
        }

        if (StringUtils.isBlank(apiModel.getToken())) {
            throw new SysException("Token can't be null");
        }

        if (!token.equals(apiModel.getToken())) {
            throw new SysException(String.format("Unknown token: %s", apiModel.getToken()));
        }
    }


    @Override
    public void refurbish(ApiModel apiModel) throws SysException {

        Logger.info("ApiServiceImpl::refurbish begin");

        this.checkToken(apiModel);

        if (StringUtils.isBlank(apiModel.getServiceId())) {
            repertoryHandler.reLoadInConsul();
            Logger.info("ApiServiceImpl::refurbish success");
        } else {
            repertoryHandler.reLoadInConsul(apiModel.getServiceId());
            Logger.info("ApiServiceImpl::refurbish for serviceId: %s", apiModel.getServiceId());
        }
    }

    @Override
    public String findData(ApiModel apiModel) throws SysException {

        this.checkToken(apiModel);

        if (StringUtils.isBlank(apiModel.getServiceId()) || StringUtils.isBlank(apiModel.getKey())) {
            throw new SysException("ServiceId or key can't be null");
        }

        String ckey = apiModel.getServiceId().replaceAll("-", "/") + "/" + apiModel.getKey();
        Logger.info("ApiServiceImpl::findData consul key: %s", ckey);

        String result = consulService.get(ckey);
        Logger.info("ApiServiceImpl::findData result: %s", result);
        return result;
    }

    @Override
    public Map<String,Object> findAll(ApiModel apiModel) throws SysException {

        this.checkToken(apiModel);

        if(StringUtils.isBlank(apiModel.getServiceId())) {
            throw new SysException("Service id can't be null");
        }

        Logger.info("ApiServiceImpl::findAll serviceId: %s", apiModel.getServiceId());
        Map<String,Object> result = repertoryHandler.findAllDataByServiceId(apiModel.getServiceId());
        return result;
    }


}
