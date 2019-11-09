/*
 * Create :2019-11-7
 * author :Aowen_Tan
 * main :测试join()方法
 * join会等待某个线程执行完成才继续执行。
 * */
package test;

public class Join {
    public volatile static int i = 0;
    public static class AddThread extends Thread{
        @Override
        public void run() {
            for(i=0;i<10000000;i++);
        }
    }

    public static void main(String[] args) throws InterruptedException{
        AddThread at = new AddThread();
        at.start();
        at.join();
        System.out.println(i);
    }
}
