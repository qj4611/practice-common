package com.practice.common.service;


import com.practice.common.dto.HaikangEvent;
import com.practice.common.dto.HaikangSubscribeEventRequest;

/**
 * @description: 海康威视回调处理服务类
 * @author: QinJun
 * @date: 2023/11/29 16:43
 */
public interface HaikangEventCallBackService {

    /**
     * 处理海康回调事件
     * @param event
     */
    void handleEvent(HaikangEvent event);

    /**
     * 订阅海康事件服务
     * @param subscribeEventRequest
     * @return
     */
    Boolean subscribeEvent(HaikangSubscribeEventRequest subscribeEventRequest);
}
