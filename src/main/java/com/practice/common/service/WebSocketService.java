package com.practice.common.service;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: websocket 服务
 * @author: QinJun
 * @date: 2023/12/22 14:17
 */
public interface WebSocketService {

    public static final ConcurrentHashMap<String, Session> clients = new ConcurrentHashMap<>();
    /**
     * 广播消息
     */
    public void sendAllMessage(String message);

    /**
     * 给指定人发送单点消息
     * @param code  标识唯一人员的code
     * @param message
     */
    public void sendOneMessage(String code, String message);
}
