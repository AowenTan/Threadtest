/*
 * Create :2019-11-13
 * author :Aowen_Tan
 * main :测试LockSupport可以响应中断 线程阻塞工具类
 * 类似于suspend() 和 resume()方法，可以使线程阻塞，但是不会出现类似于resume()出现在suspend前面导致线程永远处于阻塞状态的情况。
 * 该类可以响应中断，但不会抛出异常，可以通过Thread.interrupted()获得中断标记。
 * */
package test;

import java.util.concurrent.locks.LockSupport;

public class LockSupportIntDemo {
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
                if (Thread.interrupted()){
                    System.out.println(getName() + "被中断了");
                }
            }
            System.out.println(getName() + "执行结束");
        }
    }

    public static void main(String[] args) throws InterruptedException{
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.interrupt();
        LockSupport.unpark(t2);
    }
}
