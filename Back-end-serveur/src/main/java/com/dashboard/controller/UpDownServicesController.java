package com.dashboard.controller;

import com.dashboard.aop.DownProd;
import com.dashboard.aop.UpProd;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Service")
public class UpDownServicesController {

    /**
     * This method will start the service and send  metrics of this service
     *  to kafka
     */
    @RequestMapping("/upService")
    @UpProd
    public void up() {
        // UP
    }

    /**
     * This method will shut down  the service and send  metrics of this service
     * to kafka
     */
    @RequestMapping("/downService")
    @DownProd
    public void down() {
        //DOWN

    }
}
