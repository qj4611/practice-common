package com.practice.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class PracticeCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeCommonApplication.class, args);
    }

}
