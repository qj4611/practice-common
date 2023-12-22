package com.practice.common.controller;

import com.practice.common.dto.HaikangSubscribeEventRequest;
import com.practice.common.service.HaikangEventCallBackService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: desc
 * @author: QinJun
 * @date: 2023/11/30 16:57
 */
@RestController
public class CommonController {

    @Resource
    private  WebSocketController socketController;

    @Resource
    private HaikangEventCallBackService callBackService;




    @PostMapping("/eventSubscription")
    public Boolean eventSubscription( @RequestBody HaikangSubscribeEventRequest subscribeEventRequest){
        return callBackService.subscribeEvent(subscribeEventRequest);
    }

    @GetMapping("/test")
    public Boolean eventSubscription(){

        return callBackService.subscribeEvent(new HaikangSubscribeEventRequest());
    }
}
