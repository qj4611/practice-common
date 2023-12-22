package com.practice.common.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * @description: 海康威视查询报警注解设备列表VO
 * @author: QinJun
 * @date: 2023/12/6 10:30
 */
@Data
public class HaikangIasDeviceListQueryVO {

    //资源类型，subSys：入侵报警子系统通道，defence：入侵报警防区通道  查询报警主机通道列表时必传
    private String resourceType;

    // 名称，进行模糊搜索，最大长度32，如果包含中文，最大长度指不超过按照指定编码的字节长度，即getBytes(“utf-8”).length
    @Length(max = 32)
    private String name;

    // 区域编号，可以为空，区域编号个数<=1000个，单个长度<=64Byte，可从查询区域列表v2接口获取返回参数indexCode
    private String[] regionIndexCodes;

    // 是否搜索regionIndexCodes及其子孙区域的资源，true时搜索，false时只搜索regionIndexCodes的资源
    private boolean isSubRegion;

    // 当前页码，必须提供
    private Integer pageNo = 1;

    // 分页大小，必须提供
    private Integer pageSize = 10;

    // 排序字段，必须是查询条件，否则返回参数错误
    private String orderBy;

    // 降序升序，降序：desc，升序：asc
    private String orderType;

    /**
     * 查询表达式
     */
    List<HaikangQueryExpression> expressions;
}
