package com.yun.yunimserver.wsapi;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: yun
 * @createdOn: 2019-07-15 10:45.
 */

@Component
@ConfigurationProperties(prefix = "ws-api")
@Data
public class WsProperties {
    private String accessKey;
    private String host;
}