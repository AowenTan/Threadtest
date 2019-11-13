/*
 * Create :2019-11-13
 * author :Aowen_Tan
 * main :测试ExecutorService中的一个方法newFixedThreadPool() 创建一个固定数量线程数的线程池
 * 另外还有几个创建不同线程池的几个方法：
 * newSingleThreadExecutor() 创建只有一个线程的线程池，繁忙时任务加入任务队列等待
 * newCachedThreadPool() 可拓展的线程池，有线程空闲优先使用，没有则创建新线程
 * newSingleThreadScheduledExecutor() 给定时间内执行某项任务，具体看ScheduleExecutorServiceDemo.java
 * new ScheduleThreadPool() 也可以返回一个ScheduledExecutorService对象，但可以指定线程数量。
 * */
package test.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static class MyTask implements Runnable{
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread ID: " + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        MyTask mt = new MyTask();
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for(int i=0;i<10;i++){
            pool.submit(mt);
        }
    }
}
