/*
 * Create :2019-11-7
 * author :Aowen_Tan
 * main :测试volatile修饰的变量
 * 通过volatile修饰的变量，虚拟机会格外注意它的被修改。
 * 本案例说明某些时候volatile不能保证数据的原子性。
 * */
package test;

public class Volatile {
    static volatile int i = 0;
    public static class PlusTask implements Runnable{
        @Override
        public void run() {
            for(int k=0;k<10000;k++)
                i++;
        }
    }

    /*
    public static void main(String[] args) throws InterruptedException{
        Thread[] threads = new Thread[10];
        for(int i=0;i<10;i++){
            threads[i] = new Thread(new PlusTask());
            threads[i].start();
        }
        for(int i=0;i<10;i++){
            threads[i].join();
        }
        //结果应该输出100000，但实际输出的值总是小于100000.
        System.out.println(i);
    }
    */


    /*
    * 通过volatile解决线程中的可见性问题，若不使用volatile定义ready变量，线程将无法打印除结果。因为第44行的ready不会接收到改变消息。
    * */
    private static volatile boolean ready;
    private static int number;
    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready);
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(1000);
        number = 42;
        ready = true;
        Thread.sleep(1000);
    }
}
