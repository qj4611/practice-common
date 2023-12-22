package com.practice.common.service;

import com.alibaba.fastjson2.JSON;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * @description: desc
 * @author: QinJun
 * @date: 2023/12/7 15:50
 */
@Slf4j
public class HaikangTest extends HaikangTestParent {

    static {
        log.info("init son");
    }

    public HaikangTest(){
        log.info("init son construct");
    }

    public static void main(String[] args) {

        HaikangTest test = new HaikangTest();
    }


    public static void post(String url, Object body){

        /**
         * haikang.api.ak=27714626
         * haikang.api.sk=8h9bUpjvJ5gS5diRvPio
         * haikang.api.host=222.213.23.38:442
         * haikang.api.requestProtocol=http://
         */

    /**
     * STEP1：设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
     */
        ArtemisConfig.host = "222.213.23.38:442"; // 平台的ip端口
        ArtemisConfig.appKey = "27714626";  // 密钥appkey
        ArtemisConfig.appSecret = "8h9bUpjvJ5gS5diRvPio";// 密钥appSecret

        /**
         * STEP2：设置OpenAPI接口的上下文
         */
        final String ARTEMIS_PATH =  "/artemis";

        /**
         * STEP3：设置接口的URI地址
         */
        final String previewURLsApi = ARTEMIS_PATH + url;
        Map<String, String> path = new HashMap<String, String>(2) {
            {
                put("https://", previewURLsApi);//根据现场环境部署确认是http还是https
            }
        };

        /**
         * STEP4：设置参数提交方式
         */
        String contentType = "application/json";

        /**
         * STEP6：调用接口
         */
        String bodyStr = "";
        if(Objects.nonNull(body)){
            bodyStr = JSON.toJSONString(body);
        }
        log.info("海康请求url:{},body:{}",url,bodyStr);

        String result = ArtemisHttpUtil.doPostStringArtemis(path, bodyStr, null, null, contentType , null);

        log.info("海康请求响应：{}",result);
    }

}