package com.cad.im.bean;

/**
 * @author lijundi
 * @date 2020/10/27 11:39
 */

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:constant.properties")
@Data
public class Constant {

    @Value("wx5dfca7172706d71c")
    private String appId;

    @Value("9fc3f36f020a2123e9d9bf6634f23e38")
    private String appSecret;


}

