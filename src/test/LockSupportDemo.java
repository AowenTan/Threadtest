/*
 * Create :2019-11-13
 * author :Aowen_Tan
 * main :测试LockSupport 线程阻塞工具类
 * 类似于suspend() 和 resume()方法，可以使线程阻塞，但是不会出现类似于resume()出现在suspend前面导致线程永远处于阻塞状态的情况。
 * 该类可以响应中断，但不会抛出异常，可以通过Thread.interrupted()获得中断标记。    具体中断测试见：LockSupportIntDemo.class
 * */
package test;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name){
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (u){
                System.out.println("in " + getName());
                LockSupport.park();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        t1.start();
        Thread.sleep(100);
        t2.start();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}
