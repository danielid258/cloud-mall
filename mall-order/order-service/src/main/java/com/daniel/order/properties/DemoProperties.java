package com.daniel.order.properties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Daniel on 2018/7/3.
 */
@Data
@Component
@ConfigurationProperties(prefix = "com.daniel")
@RefreshScope   //仓库中的配置改变时 有@RefreshScope注解的配置信息会自动刷新, 实现修改配置文件但是不需要重启应用
public class DemoProperties {
    @JsonProperty("danielName")
    private String name;

    @JsonProperty("danielAge")
    private Integer age;
}
