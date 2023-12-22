package com.practice.common.controller;


import com.practice.common.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: QinJun
 * @date: 2023/11/30 10:19
 */
@Controller
@Slf4j
@ServerEndpoint("/websocket/{code}")
public class WebSocketController {

    @OnOpen
    public void afterConnectionEstablished(Session session , @PathParam("code")String code) throws Exception {
        log.info("session:{},建立socket连接", code);
        WebSocketService.clients.put(code, session);

    }

    @OnClose
    public void afterConnectionClosed(Session session,@PathParam("code") String code) throws Exception {
        log.info("session:{},关闭socket连接", code);
        if(Objects.nonNull(session) &&session.isOpen()){
            WebSocketService.clients.remove(session);
        }
    }



}
