package com.practice.common.service;

import com.practice.common.dto.HaikangEvent;
import com.practice.common.dto.HaikangEventType;
import com.practice.common.dto.HaikangSubscribeEventRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @description: desc
 * @author: QinJun
 * @date: 2023/11/29 16:48
 */
@Service
@Slf4j
public class HaikangEventCallBackServiceImpl implements HaikangEventCallBackService {

    @Resource
    private List<HaikangEventHandler>  eventHandlers;

    @Resource
    private HaikangEventCommonHandler commonHandler;


    @Resource
    private HaikangAlertEventHandler alertEventHandler;

    @Resource
    private WebSocketService socketService;

    @Override
    @Async("commonExecutor") //异步处理，因为海康回调需要立即返回请求响应
    public void handleEvent(HaikangEvent event) {

        getHandler(HaikangEventType.getByCode(event.getEventType())).handle(event);

    }

    @Override
    public Boolean subscribeEvent(HaikangSubscribeEventRequest subscribeEventRequest) {
        socketService.sendOneMessage("123","123木头人");
        alertEventHandler.handle(new HaikangEvent());
        return true;
    }


    private HaikangEventHandler getHandler(HaikangEventType eventType){
        if(Objects.isNull(eventType)){
            throw new RuntimeException("没有对应的事件类型，请添加");
        }
        for (HaikangEventHandler eventHandler : eventHandlers) {
            if(eventType.equals(eventHandler.getMappingEventType())){
                return eventHandler;
            }
        }
        return commonHandler;
    }

}
