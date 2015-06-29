package com.junesix.api.frame.help.controllers;

import com.alibaba.fastjson.JSONObject;
import com.junesix.common.config.DefaultConfigLoader;
import com.junesix.common.context.RequestContext;
import com.junesix.common.utils.log.ApiLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-04-29 16:19
 */
@RestController
@RequestMapping("/help")
public class HelpController {

    private static final Logger logger = LoggerFactory.getLogger(HelpController.class.getName());

    @RequestMapping(value = "/ping")
    public String ping(RequestContext rc) {
        ApiLogger.info(rc.getRequestId());
        return "{\"apistatus\":1,\"result\":true}";
    }

    @RequestMapping(value = "/echo", method = RequestMethod.POST)
    public String echo(@RequestParam String msg) {
        if (DefaultConfigLoader.getInstance().isDevEnv()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log();

        JSONObject result = new JSONObject();
        result.put("apistatus", 1);

        JSONObject msgJson = new JSONObject();
        msgJson.put("msg", msg);
        result.put("result", msgJson);

        return result.toJSONString();
    }

    public void log() {
        logger.debug("debug test");
        logger.info("info test");
        logger.warn("warn test");
        logger.error("error test");
    }
}
