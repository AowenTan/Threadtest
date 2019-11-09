/*
 * Create :2019-11-9
 * author :Aowen_Tan
 * main :测试FairLock 公平锁
 * 公平锁其实是通过synchronized的一个带参方法实现的，只需要把构造方法中的参数设置为true，就可以实现公平锁。
 *
 * */
package test.lock;

import java.util.concurrent.locks.ReentrantLock;

public class FairLock implements Runnable{
    public static ReentrantLock fairLock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true){
            try {
                fairLock.lock();
                System.out.println(Thread.currentThread().getName() + "获得锁");
            }finally {
                fairLock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        FairLock rl = new FairLock();
        Thread t1 = new Thread(rl,"Thread_t1");
        Thread t2 = new Thread(rl,"Thread_t2");
        t1.start();
        t2.start();
    }
}
