package com.practice.common.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

@Component
public class ReferenceA {


    @Lazy
//    @Resource(name = "referenceB",type = ReferenceA.class)
    private ReferenceB referenceB;

    static {

    }
//
//    @Autowired
    public static void   test(){
        int i = 0;

        String a = "";

    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

       Thread thread1 = new Thread(() -> {
           System.out.println("thread1 set threadLocal before:" + threadLocal.get());
          threadLocal.set(1);
           System.out.println("thread1 get local:"+ threadLocal.get());
       });

        Thread thread2 = new Thread(() -> {
            System.out.println("thread2 set threadLocal before:" + threadLocal.get());
            threadLocal.set(4);
            throw new RuntimeException("抛出错误啦");
//            System.out.println("thread2 get local:"+ threadLocal.get());
        });

        Callable callable = new Callable<Integer>(){

            @Override
            public Integer call() throws Exception {
                return 1;
            }
        };

        FutureTask futureTask = new FutureTask(callable);

        Thread a = new Thread(futureTask);
        a.start();


        Map<String,String> map = new HashMap<>();
        map.put(null,null);


        thread1.start();
        thread2.start();
        try{
            thread1.join();
            thread2.join();
        }catch (InterruptedException exception){
            System.out.println(exception);
        }

        String ggg = new String("a");
        String b = "b";
        System.out.println("b" == b);
        System.out.println("ab" == a+b);

        synchronized (a){
            Integer x = 1;
            Integer y = 1;

            System.out.println("over"  + (x == y));

            x = 139;
            y = 139;
            System.out.println("over2"  + (x == y));

        }


    }
}
