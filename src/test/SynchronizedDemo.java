/*
 * Create :2019-11-7
 * author :Aowen_Tan
 * main :测试synchronized 定义的同步块
 * 通过synchronized定义的代码块同一时间只能允许一个线程对其进行访问。
 * synchronized的三种用法：
 * 修饰实例方法，作用于当前对象实例加锁
 * 修饰静态方法，作用于当前类对象加锁
 * 修饰代码块，指定加锁对象，对给定对象加锁。
 * */
package test;

public class SynchronizedDemo implements Runnable{
    static SynchronizedDemo instance = new SynchronizedDemo();
    static int i = 0;

    public synchronized void increase(){
        i++;
    }

    @Override
    public void run() {
        for(int j=0;j<10000000;j++){
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
