//package com.practice.common.config;
//
//import com.practice.common.service.HaikangAlertEventHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.*;
//
///**
// * @description: webSocket配置
// * @author: QinJun
// * @date: 2023/12/21 14:50
// */
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//
//    /**
//     * 注册 Stomp的端点 配置对外暴露访问的端点
//     * @param registry
//     */
//    @Override
//     public void registerStompEndpoints(StompEndpointRegistry registry) {
//            registry.addEndpoint("/websocket-simple")   //添加STOMP协议的端点。
//                    // 这个URL是供WebSocket客户端或SockJS客户端连接服务端访问的地址。
//                    .setAllowedOrigins("*") //添加允许跨域访问
//                    .withSockJS();  //指定端点使用SockJS协议
//    }
//
//    /**
//     * 配置消息代理
//     * @param registry
//     */
//    @Override
//     public void configureMessageBroker(MessageBrokerRegistry registry) {
//         //启动简单Broker，客户端请求地址符合配置的前缀，消息才会发送到这个broker
//         //客户端订阅当前服务端时需要添加以下请求前缀，topic一般用于广播推送，queue用于点对点推送
//         registry.enableSimpleBroker("/alert","/alertTopic");
//         //如果不设置下面这一句，使用SimpMessagingTemplate.convertAndSendToUser向指定用户推送消息时
//         //订阅前缀只能为/user，例如前端订阅为/user/fanqi/info
//         registry.setUserDestinationPrefix("/alert");
//         //客户端（html客户端、java客户端等）向服务端发送消息的请求前缀
//         registry.setApplicationDestinationPrefixes("/app");
//     }
//}
