package service;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/configuration")

public class Service1Controller {

    private static final Logger LOOGER = LoggerFactory.getLogger(Service1Controller.class);

    @GetMapping("/up")
    public BoleanResponse up() {
        System.out.println("Current status : " + ApplicationInfoManager.getInstance().getInfo().getStatus());
        ApplicationInfoManager.getInstance().setInstanceStatus(InstanceInfo.InstanceStatus.UP);
        return BoleanResponse.builder().bol(Boolean.TRUE).build();
    }

    @GetMapping("/down")
    public BoleanResponse down() {
        System.out.println("Current status : " + ApplicationInfoManager.getInstance().getInfo().getStatus());
        ApplicationInfoManager.getInstance().setInstanceStatus(InstanceInfo.InstanceStatus.DOWN);
        return BoleanResponse.builder().bol(Boolean.TRUE).build();
    }

}
