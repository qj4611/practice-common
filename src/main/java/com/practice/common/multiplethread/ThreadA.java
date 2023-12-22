package com.practice.common.multiplethread;

import com.practice.common.config.ThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: desc
 * @author: Francis
 * @date: 2023/7/28 18:33
 */
@Slf4j
public class ThreadA extends Thread{

    public static volatile Integer sync = 100;

    @Lazy
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        int[][] coordinates = new int[][]{{0,0},{0,1},{0,-1}};
//        log.info("check result:{}",checkStraightLine(coordinates));

//        int[] a = new int[]{1,2,3};
//        int[] b = new int[]{0,0,1};
//        int[] c = new int[]{0,1,-1};
//        int[] d = new int[]{5,4,3};
//        log.info("a:{},b:{},c:{},d:{}",checkSortedIntArry(a),checkSortedIntArry(b),checkSortedIntArry(c),checkSortedIntArry(d));

        ThreadPool pool = new ThreadPool();
        ThreadPoolExecutor poolExecutor = pool.getThread();
        poolExecutor.shutdown();

        Condition condition = new ReentrantLock(false).newCondition();
        condition.signal();
        condition.await();



        CountDownLatch countDownLatch = new CountDownLatch(20);
//        for (int i = 1; i < 100; i = i++){
//            SomeRun someRun = new SomeRun(countDownLatch,i);
//            SomeCall someCall = new SomeCall(countDownLatch,i);
//            poolExecutor.execute(someRun);
//            poolExecutor.submit(someCall);
//        }




        log.info("start");
//        SomeCall someCall = new SomeCall(countDownLatch,100);
//        Future<String> submit = poolExecutor.submit(someCall);
////        log.info("execute result call:" +submit.get());
//        Future<?> submit1 = poolExecutor.submit(new SomeRun(countDownLatch, 100));

//        log.info("execute result run:" +submit1.get());
//        countDownLatch.await();
//        log.info("execute result call:" +submit.get());
//        log.info("execute result run:" +submit1.get());
//        log.info("complete thread execute ");

        ThreadA threadA = new ThreadA();
        threadA.hashCode();



        new Thread(() ->threadA.printABC(2),"C").start();
        new Thread(() ->threadA.printABC(1),"B").start();
        new Thread(() ->threadA.printABC(0),"A").start();

    }

    public static boolean checkStraightLine(int[][] coordinates) {
        BigDecimal y1 = new BigDecimal(coordinates[0][1]);
        BigDecimal y2 = new BigDecimal(coordinates[1][1]);
        BigDecimal x1 = new BigDecimal(coordinates[0][0]);
        BigDecimal x2 = new BigDecimal(coordinates[1][0]);

        BigDecimal k = (y1.subtract(y2))
                .divide(x1.subtract(x2),6, RoundingMode.HALF_UP);
        BigDecimal c = (y1.add(y2).subtract(k.multiply(x1.add(x2))));

        for (int[] coordinate : coordinates) {
            if(!new BigDecimal(coordinate[0]).multiply(k).add(c).equals(new BigDecimal(coordinate[1]))){
                return false;
            }
        }

        return true;
    }

    static class SomeRun implements Runnable{

        public  static  final  Object A = new Object();

        public SomeRun(CountDownLatch cdl,Integer current){
            this.cdl = cdl;
            this.current =current;
        }

        public SomeRun(Integer targetNum){
            this.current =current;
            this.targetNum =targetNum;
        }

        public SomeRun(Integer current,Integer targetNum){
            this.current =current;
            this.targetNum =targetNum;
        }

        private int num;
        private Integer targetNum;

        private volatile Integer current;
        private CountDownLatch cdl;

        @Override
        public void run() {
//                Thread.sleep(1000L);
            synchronized (A){
                while (num % 2 == targetNum){
                    try {
                        A.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                log.info(num + Thread.currentThread().getName());
                num++;
                A.notifyAll();

            }
        }
    }

    static class SomeCall implements Callable<String>{

        public SomeCall(CountDownLatch cdl,Integer current){
            this.cdl = cdl;
            this.current = current;
        }

        private volatile Integer current;

        private CountDownLatch cdl;

        @Override
        public String call() throws Exception {
//            Thread.sleep(5000);
            log.info("current:{}",current);
            if(1 == 0){
                throw new Exception("");
            }

            Object B = new Object();
            cdl.countDown();
            while (sync % 2 == 0 && current > 0){
                synchronized (B){
                    log.info(" B current:{},count in run:{}",current, cdl.getCount());
                    sync--;
                }
            }

            return "ok";
        }
    }

    public static final Object LOCK =new Object();

    int num = 0;

    private void printABC(int targetNum) {
        for (int i = 0; i< 10;i++){
//            log.info("loopNum:{}",i);
            synchronized (LOCK) {
//            System.out.print("target: "+targetNum+" whilebeforenum:" +num);
//            while (num % 3 != targetNum) {    //想想这里为什么不能用if代替while，想不起来可以看公众号上一篇文章
//                try {
//                    LOCK.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.print("target: "+targetNum+"num in loop:" +num);
//            }
//            num++;
//            System.out.print(Thread.currentThread().getName());
//            LOCK.notifyAll();

                while (num % 3 != targetNum){
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.print(Thread.currentThread().getName() +   i + "," );
                num++;
                LOCK.notifyAll();
            }
//            LOCK.notifyAll();
        }



    }

}
