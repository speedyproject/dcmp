package org.nicosoft.config.app.controller;

import org.nicosoft.config.support.model.ResultBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Consul health check url
 *
 * @author nico
 * @since 2017.8.28
 */
@RestController
@RequestMapping("/")
public class HealthController {

    @RequestMapping("health")
    public ResultBody health() {
        ResultBody result = new ResultBody(ResultBody.SUCCESS, "success");
        return result;
    }
}
