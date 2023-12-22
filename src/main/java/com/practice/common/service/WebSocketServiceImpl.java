package com.practice.common.service;

import org.springframework.stereotype.Service;

import javax.websocket.Session;

/**
 * @description: desc
 * @author: QinJun
 * @date: 2023/12/22 14:18
 */
@Service
public class WebSocketServiceImpl implements WebSocketService {

    /**
     * 广播消息
     */
    @Override
    public void sendAllMessage(String message) {
        for (Session session : clients.values()) {
            if (session != null && session.isOpen()) {
                session.getAsyncRemote().sendText(message);
            }
        }
    }

    /**
     * 给指定人发送单点消息
     *
     * @param message
     */
    @Override
    public void sendOneMessage(String code, String message) {
        Session session = clients.get(code);
        //在发送数据之前先确认 session是否已经打开 使用session.isOpen() 为true 则发送消息
        if (session != null && session.isOpen()) {
            session.getAsyncRemote().sendText(message);
        }
    }
}
