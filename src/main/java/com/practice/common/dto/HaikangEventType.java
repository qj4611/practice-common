package com.practice.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @description: 入侵报警事件类型
 * @author: QinJun
 * @date: 2023/11/29 15:53
 */
@Getter
@AllArgsConstructor
public enum HaikangEventType {

    DO_SET(327937,"布防"),
    UNDO_SET(327938,"撤防"),
    CANCEL_ALERT(327941,"消警"),
    BYPASS(327939,"旁路"),
    BYPASS_RECOVER(327940,"旁路恢复"),
    ALERT(327681,"报警"),
    ;

    int code;
    String description;

    public static HaikangEventType getByCode(Integer code){
        if(Objects.isNull(code)){
            throw new RuntimeException("code为空");
        }
        HaikangEventType[] values = HaikangEventType.values();
        for (HaikangEventType value : values) {
            if(code.equals(value.getCode())){
                return value;
            }
        }

        throw new RuntimeException("找不到对应的事件类型，请添加");
    }

}
