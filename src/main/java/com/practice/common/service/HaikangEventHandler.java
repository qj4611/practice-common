package com.practice.common.service;


import com.practice.common.dto.HaikangEvent;
import com.practice.common.dto.HaikangEventType;

/**
 * @description: 海康威视事件处理通用接口
 * @author: QinJun
 * @date: 2023/11/29 16:44
 */

public interface HaikangEventHandler {


//    void handle(HaikangEvent event);

    HaikangEventType getMappingEventType();

    /**
     * 默认处理方法
     * @param event
     */
    default void handle(HaikangEvent event){
        //TODO 默认处理逻辑
        System.out.println("默认处理逻辑++++++++++++++111");
    }
}
