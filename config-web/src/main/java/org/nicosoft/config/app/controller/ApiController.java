package org.nicosoft.config.app.controller;

import org.nicosoft.config.app.model.ApiModel;
import org.nicosoft.config.app.service.ApiService;
import org.nicosoft.config.support.exception.SysException;
import org.nicosoft.config.support.model.ResultBody;
import org.nicosoft.config.support.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Api interface controller
 *
 * @author nico
 * @since 2017.8.29
 */
@RestController
@RequestMapping("api")
public class ApiController {

    @Autowired
    ApiService apiService;

    /**
     * Refurbish KV data
     *
     * @param apiModel
     * @return
     */
    @PostMapping({"refurbish", "refurbish.json"})
    public ResultBody refurbish(@RequestBody ApiModel apiModel) {
        ResultBody result = new ResultBody(ResultBody.SUCCESS, "success");
        try {
            apiService.refurbish(apiModel);
        } catch (SysException e) {
            result = new ResultBody(ResultBody.FAILED, e.getMessage());
            Logger.error("ApiController::refurbish error: %s", e.getMessage());
        }
        return result;
    }

    /**
     * Find value for key
     *
     * @param apiModel
     * @return
     */
    @PostMapping({"find/value", "find/value.json"})
    public ResultBody findValue(@RequestBody ApiModel apiModel) {
        ResultBody result = new ResultBody(ResultBody.SUCCESS, "success");
        try {
            String value = apiService.findData(apiModel);
            result.setResult(value);
        } catch (SysException e) {
            result = new ResultBody(ResultBody.FAILED, e.getMessage());
            Logger.error("ApiController::findValue error: %s", e.getMessage());
        }
        return result;
    }

    /**
     * Find all data
     *
     * @param apiModel
     * @return
     */
    @PostMapping({"find/all", "find/all.json"})
    public ResultBody findAll(@RequestBody ApiModel apiModel) {
        ResultBody result = new ResultBody(ResultBody.SUCCESS, "success");
        try {
            Map<String, Object> objectMap = apiService.findAll(apiModel);
            result.setResult(objectMap);
        } catch (SysException e) {
            result = new ResultBody(ResultBody.FAILED, e.getMessage());
            Logger.error("ApiController::findAll error: %s", e.getMessage());
        }
        return result;
    }


}







