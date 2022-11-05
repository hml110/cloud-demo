package com.hml.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hml
 * @version 1.0
 * @description:
 * @date 2022/11/5 15:57
 */
@Data
@Component
@ConfigurationProperties(prefix = "pattern") //约定大于配置
public class PatternProperties {
    private String dateformet;
    private String envShareValue;
    private String name;
}
