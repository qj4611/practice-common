package com.practice.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @description: desc
 * @author: Francis
 * @date: 2023/8/2 8:35
 */
@Slf4j
@Configuration
public class ThreadPool {


    @Bean
    public ThreadPoolExecutor getThread(){

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,5,5L, TimeUnit.SECONDS,new ArrayBlockingQueue<>(100));

        RejectedExecutionHandler rejectHandler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                executor.remove(r);
                log.info("任务队列已经满了！！！");
                throw new RejectedExecutionException("任务队列已经满了");
            }
        };


        return poolExecutor;
    }


}
