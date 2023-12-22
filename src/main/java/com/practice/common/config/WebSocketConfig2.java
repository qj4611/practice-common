package com.practice.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @description: webSocket配置
 * @author: QinJun
 * @date: 2023/12/21 14:50
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig2 {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
