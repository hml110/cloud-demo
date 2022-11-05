package com.hml.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;


/**
 * @author hml
 * @version 1.0
 * @description: 基于Java代码来修改Feign日志级别
 * @date 2022/11/5 20:28
 */
public class DefaultFeignConfiguration {
    //注意Logger.Level的包为 import feign.Logger;
    @Bean
    public Logger.Level logLevel(){
        return Logger.Level.BASIC;
    }
}
