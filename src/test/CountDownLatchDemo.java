/*
 * Create :2019-11-11
 * author :Aowen_Tan
 * main :测试CountDownLatch 倒计时器
 * 类似于火箭发射前的检查，需要10个线程全部完成，主线程方可执行。
 *
 * */
package test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo implements Runnable{
    static final CountDownLatch end = new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println("check complete");
            end.countDown();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i=0;i<10;i++){
            exec.submit(demo);
        }
        //等待检车
        end.await();
        //火箭发射
        System.out.println("Fire!");
        exec.shutdown();
    }
}
