package com.practice.common.dto;

import lombok.Data;

import java.util.List;

/**
 * @description: 海康查询表达式
 * @author: QinJun
 * @date: 2023/12/7 16:12
 */
@Data
public class HaikangQueryExpression {

    /**
     * 资源属性名，目前支持作为查询条件的字段包括: channelNo、indexCode、parentIndexCode、updateTime、createTime、resourceType、regionIndexCode、sort、name、description，例：key传updateTime，operator传between可以查询特定时间段更新的数据，考虑到校时和夏令时，建议值查询过去一天的数据变更
     */
    private String key;
    /**
     * 操作运算符，操作运算符,
     * 0 ：=,
     * 1 ：>=,
     * 2 ：<=,
     * 3 ：in,查询范围在输入参数范围;
     * 4 ：not in,查询范围不在输入参数范围;
     * 5 ：between,查询范围在输入的两个参数之间;
     * 6 ：like,模糊查询;
     * 7 ：pre like,前置%模糊查询;
     * 8 ：suffix like,后置%模糊查询;
     * 21 ：not null,存在该属性且不为空
     */
    private int operator;

    /**
     * 资源属性值，=、>=、<=、like、values数组长度只能是1；
     * in、not in，values数组长度大于1，最大不超时20；
     * in_array用于查询key值有多个value的情况，例如行车监控添加的设备类型为encodeDevice、encodeDeviceMss两个类型，使用encodeDevice或者encodeDeviceMss都可以查询到；between只能用于整形、日期
     * ；like只能用于字符串。
     */
    private List<String> values;
}
