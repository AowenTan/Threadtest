/*
 * Create :2019-11-13
 * author :Aowen_Tan
 * main :测试newScheduleThreadPool方法中的scheduleAtFixedRate()方法
 * 可以实现在指定周期内执行任务，若运行时间超过周期时间，则下一个任务从这个任务结束后紧接着执行
 * scheduleWithFixedDelay()方法是运行一个任务结束后经过delay时间再执行下一个任务。
 * */
package test.ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleExecutorServiceDemo {
    public static void main(String[] args){
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println(System.currentTimeMillis()/1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },0,2, TimeUnit.SECONDS);
    }
}
