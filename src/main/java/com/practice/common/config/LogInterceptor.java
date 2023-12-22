//package com.practice.common.config;
//
//import jakarta.servlet.DispatcherType;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.nio.charset.Charset;
//
///**
// * @description: desc
// * @author: QinJun
// * @date: 2023/11/30 19:30
// */
//@Slf4j
//public class LogInterceptor implements HandlerInterceptor {
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if(!request.getDispatcherType().equals(DispatcherType.REQUEST)){
//            return true;
//        }
//
////        Map<String, String[]> parameterMap = request.getParameterMap();
////        for (Map.Entry<String, String[]> stringEntry : parameterMap.entrySet()) {
////            log.info("param key:{},param value:{}",stringEntry.getKey(),stringEntry.getValue().length > 0 ? stringEntry.getValue()[0] : "");
////        }
////
//        if(request.getMethod().toLowerCase().equals("get")){
//            return true;
//        }
//        String bodyString = getBodyString(request);
//        log.info("body:{}",bodyString);
//
//
//        return true;
//    }
//
//    public  String getBodyString(HttpServletRequest request) throws IOException {
//        StringBuilder sb = new StringBuilder();
//        InputStream inputStream = null;
//        BufferedReader reader = null;
//        try {
//            inputStream = request.getInputStream();
//            if( 0 == inputStream.available()){
//                return sb.toString();
//            }
//            reader = new BufferedReader(
//                    new InputStreamReader(inputStream, Charset.forName("UTF-8")));
//
//            char[] bodyCharBuffer = new char[1024];
//            int len = 0;
//            while ((len = reader.read(bodyCharBuffer)) != -1) {
//                sb.append(new String(bodyCharBuffer, 0, len));
//            }
//            inputStream.reset();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//        return sb.toString();
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//}
