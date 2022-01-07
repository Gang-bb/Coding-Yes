package com.gangbb.contentcenter.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * user-center用户中心的Feign配置。7.3章节学习
 *  这个类别加@Configuration注解了，否则必须挪到@ComponentScan能扫描的包以外
 * 
 * @author Gangbb
 * @date 2022/1/7
 **/
public class UserCenterFeignConfiguration {

    @Bean
    public Logger.Level level(){
        // 让feign打印所有请求的细节
        return Logger.Level.FULL;
    }
}