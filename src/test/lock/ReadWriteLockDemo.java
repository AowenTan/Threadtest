/*
 * Create :2019-11-11
 * author :Aowen_Tan
 * main :测试ReadWriteLock 读写锁
 * 读写锁允许读读操作并行执行，提高效率，具体效果可以通过更换48和59行代码为49格60行运行可知，
 * 前者使用readLock和writeLock运行速度明显比后者使用lock的速度大大加快了。
 * */
package test.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;

    public Object handleRead(Lock lock) throws InterruptedException{
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        }finally{
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock,int index)throws InterruptedException{
        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();
        Runnable readRunnale = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handleRead(readLock);
//                    demo.handleRead(lock);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        Runnable writeRunnale = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handleWrite(writeLock,new Random().nextInt());
//                    demo.handleWrite(lock,new Random().nextInt());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        for (int i=0;i<18;i++){
            new Thread(readRunnale).start();
        }

        for (int i=18;i<20;i++){
            new Thread(writeRunnale).start();
        }
    }
}
