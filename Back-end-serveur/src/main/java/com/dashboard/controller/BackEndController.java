package com.dashboard.controller;

import com.dashboard.aop.CONS;
import com.dashboard.aop.PROD;
import com.dashboard.modal.UserDetails;
import com.netflix.appinfo.ApplicationInfoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController

@RequestMapping("/welcome")
public class BackEndController {
     final Logger logger = (Logger) LoggerFactory.getLogger(BackEndController.class);
    @RequestMapping(value = "/cons", method = RequestMethod.GET)
    @CONS
    public String info() {
        return "welcome " + String.valueOf(ApplicationInfoManager.getInstance().getInfo().getPort()) + "  has responded";
    }
    @RequestMapping(value = "/prod", method = RequestMethod.POST)
    @PROD
    public void producer(@RequestBody UserDetails userDetails){




    }




}
