package qqzsh.top.contentcenter.configuration;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;
import ribbonconfiguration.RibbonConfiguration;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-09 15:42
 * @Description 用户中心Ribbon配置
 */
@Configuration
//@RibbonClient(name = "user-cnter",configuration = RibbonConfiguration.class)
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class UserCenterRibbonConfiguration {

}
