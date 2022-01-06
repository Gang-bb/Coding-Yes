package com.gangbb.contentcenter.configuration;

import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;
import ribbonconfiguration.RibbonConfiguration;

/**
 * @author Gangbb
 * @date 2021/4/22 9:43
 * @Description:
 */

@Configuration
//@RibbonClient(name = "user-center", configuration = RibbonConfiguration.class)
@RibbonClients(defaultConfiguration= RibbonConfiguration.class)
public class UserCenterRibbonConfig {

}
