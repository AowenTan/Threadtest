/*
 * Create :2019-11-8
 * author :Aowen_Tan
 * main :测试ReenterLock 重入锁
 * synchronized的功能扩展，可以手动上锁
 * 注意：上多少锁最后要释放对应数量的锁
 *
 * */
package test.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReenterLock implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        for (int j=0;j<10000000;j++){
            lock.lock();
            try {
                i++;
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ReenterLock tl = new ReenterLock();
        Thread t1 = new Thread(tl);
        Thread t2 = new Thread(tl);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
