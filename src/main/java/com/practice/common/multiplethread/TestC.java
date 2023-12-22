package com.practice.common.multiplethread;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: desc
 * @author: Francis
 * @date: 2023/8/2 16:22
 */
public class TestC {

    static volatile boolean sprintA =true;
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        

        Thread thread1 = new Thread(() -> {
            while (true){
                if(sprintA){
                    LockSupport.park();
                    sprintA =false;
                    System.out.print("A");
                }


                LockSupport.unpark(Thread.currentThread());
            }
        }
        ,"spring A");

        Thread.State state = thread1.getState();


        Thread thread2 = new Thread(() ->{
            while (true){
                if(!sprintA){
                    LockSupport.park();
                    System.out.print("B");
                    sprintA = true;
                }

                LockSupport.unpark(Thread.currentThread());
            }

        },"spring B");


        thread2.start();
        thread1.start();
    }
}
