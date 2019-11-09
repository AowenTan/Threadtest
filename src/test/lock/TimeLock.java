/*
 * Create :2019-11-8
 * author :Aowen_Tan
 * main :测试Trylock 等待锁
 * tryLock有两种使用方法，有参和无参，具体看下面代码。
 *
 * */
package test.lock;

import java.util.concurrent.locks.ReentrantLock;

public class TimeLock implements Runnable{
    //带参的tryLock方法
    /*
    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
        //tryLock方法的两个参数，第一个是数值，第二个是单位
            if(lock.tryLock(5, TimeUnit.SECONDS)){
                Thread.sleep(6000);
            }else{
                System.out.println("get lock failed");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            if (lock.isHeldByCurrentThread())
                lock.unlock();
        }
    }

    public static void main(String[] args){
        TimeLock tl = new TimeLock();
        Thread t1 = new Thread(tl);
        Thread t2 = new Thread(tl);
        t1.start();
        t2.start();
    }
    */

    //不带参的tryLock()方法
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public TimeLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        if (lock == 1){
            while (true){
                if (lock1.tryLock()){
                    try {
                        try {
                            Thread.sleep(500);
                        }catch (InterruptedException e){
                        }
                        if (lock2.tryLock()){
                            try {
                                System.out.println(Thread.currentThread().getId() + ":My Job done");
                                return;
                            }finally {
                                lock2.unlock();
                            }
                        }
                    }finally {
                        lock1.unlock();
                    }
                }
            }
        }else {
            while (true){
                if (lock2.tryLock()){
                    try {
                        try {
                            Thread.sleep(500);
                        }catch (InterruptedException e){
                        }
                        if (lock1.tryLock()){
                            try {
                                System.out.println(Thread.currentThread().getId() + ":My Job done");
                                return;
                            }finally {
                                lock1.unlock();
                            }
                        }
                    }finally {
                        lock2.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        TimeLock r1 = new TimeLock(1);
        TimeLock r2 = new TimeLock(2);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }
}
