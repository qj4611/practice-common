package com.practice.common.service;


import com.practice.common.dto.HaikangEvent;
import com.practice.common.dto.HaikangEventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description: 海康威视报警事件处理
 * @author: QinJun
 * @date: 2023/11/30 10:19
 */
@Service
@Slf4j
//@ServerEndpoint("/websocket")
public class HaikangAlertEventHandler  implements HaikangEventHandler {


    @Override
    public void handle(HaikangEvent event) {


        log.info("执行报警处理逻辑");
    }

    @Override
    public HaikangEventType getMappingEventType() {
        return HaikangEventType.ALERT;
    }



}
