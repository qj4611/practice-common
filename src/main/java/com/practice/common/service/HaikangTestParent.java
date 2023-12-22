package com.practice.common.service;

import lombok.extern.slf4j.Slf4j;


/**
 * @description: desc
 * @author: QinJun
 * @date: 2023/12/7 15:50
 */
@Slf4j
public class HaikangTestParent {

    static {
        log.info("init parent");
    }

    public HaikangTestParent(){
        log.info("init parent construct");
    }
}