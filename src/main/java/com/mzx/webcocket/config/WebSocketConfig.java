package com.mzx.webcocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author ZhenXinMa
 * @date 2020/7/24 13:47
 */
@Configuration
public class WebSocketConfig {

    /*
     * --------------------------------------------------------
     * 为了能够让SpringBoot支持Websocket，我们需要创建一个Bean，该bean
     * 能自动扫描添加了@ServerEndpoint注解的类.
     * --------------------------------------------------------
     * */

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }


}
