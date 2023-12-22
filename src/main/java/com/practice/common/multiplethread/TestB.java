package com.practice.common.multiplethread;

import java.math.BigDecimal;

/**
 * @description: desc
 * @author: Francis
 * @date: 2023/8/2 12:01
 */
public class TestB {

    private int num;

    private static final Object LOCK = new Object();
    private void printABC(int targetNum) {
        for (int i = 0; i < 10; i++) {
            synchronized (LOCK) {
                while (num % 3 != targetNum) { //想想这里为什么不能用if代替，想不起来可以看公众号上一篇文章
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                num++;
                System.out.print(Thread.currentThread().getName());
                LOCK.notifyAll();
            }
        }
    }
    public static void main(String[] args) {
        final Object lock = new Object();
        final int[] count = new int[]{0};


        Thread threadA = new Thread(() -> {
            while (true){
                synchronized (lock) {
                    if(count[0] == 100){
                        lock.notifyAll();
                        break;
                    }
                    if(count[0] % 2 ==  0){
                        System.out.print("A");
                        count[0]++;
                    }
                    lock.notifyAll();
                    try {
                        lock.wait();
                    }catch (InterruptedException ex){}
                }
            }

        });

        Thread threadB = new Thread(() ->{
            while (true){
                synchronized (lock){
                    if(count[0] == 100){
                        lock.notifyAll();
                        break;
                    }
                    if(count[0] % 2 ==1){
                        System.out.print("B");
                        count[0]++;
                    }

                    lock.notifyAll();
                    try {
                        lock.wait();
                    }catch (InterruptedException ex){}
                }
            }

        },"sprint B");

        System.out.println("start");

        threadB.start();
        try {
            Thread.sleep(100);
        }catch (Exception ex){

        }
        threadA.start();

        try {
         threadB.join();
         threadA.join();
        }catch (InterruptedException exception){}

        System.out.println("done");
        System.out.println(BigDecimal.valueOf(3.0).equals(BigDecimal.valueOf(3)));

    }
}
