package com.practice.common.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: QinJun
 * @date: 2023/11/29 15:43
 */
@Data
public class HaikangEvent implements Serializable {

        /**
         * 事件详情，实际返回与设备驱动有关，不与“报警 布防 撤防 旁路 旁路恢复 消警”完全对应，建议不做解析。若第三方驱动中没有此字段，则事件报文中也没有此字段。
         */
        private String data;

        /**
         * 事件唯一标识
         */
        private String eventId;

        /**
         * 事件源编号，物理设备是资源编号
         */
        private String srcIndex;

        /**
         * 事件源类型，可以为defence或subSys @see com.dianxin.device.api.device.enums.HaikangEventSourceType
         */
        private String srcType;

        /**
         * 事件源名称
         */
        private String srcName;

        /**
         * 事件类型  @SEE com.dianxin.device.api.device.enums.HaikangEventType
         */
        private Integer eventType;

        /**
         * 事件状态，0-瞬时，1-开始，2-停止，3-事件脉冲，4-事件联动结果更新
         */
        private Integer status;

        /**
         * 脉冲超时时间，单位:秒
         */
        private int timeout;

        /**
         * 事件发生时间（设备时间）
         */
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime happenTime;

        /**
         * 事件发生的事件源父设备编号
         */
        private String srcParentIndex;

}
