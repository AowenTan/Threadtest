/*
 * Create :2019-11-8
 * author :Aowen_Tan
 * main :测试condition
 * Condition中提供7个方法，可以实现线程等待和唤醒状态，类似于wait()和notify()方法
 *
 * */
package test.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockCondition implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.println("Thread is going on");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ReenterLockCondition tl = new ReenterLockCondition();
        Thread t1 = new Thread(tl);
        t1.start();
        Thread.sleep(2000);
        //通知线程t1继续执行
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
