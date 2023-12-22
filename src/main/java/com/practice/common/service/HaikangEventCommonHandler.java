package com.practice.common.service;

import com.practice.common.dto.HaikangEventType;
import org.springframework.stereotype.Service;

/**
 * @description: desc
 * @author: QinJun
 * @date: 2023/11/30 15:07
 */
@Service
public class HaikangEventCommonHandler implements HaikangEventHandler {
    @Override
    public HaikangEventType getMappingEventType() {
//        HaikangEventHandler.super.handle(new HaikangEvent());
        return null;
    }

}
