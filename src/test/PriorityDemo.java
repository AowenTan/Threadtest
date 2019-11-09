/*
 * Create :2019-11-7
 * author :Aowen_Tan
 * main :测试设置线程优先级
 * 线程优先级有三个预设值，分别是MIN_PRIORITY=1   NORM_PRIORITY=5   MAX_PRIORITY=10
 * 本案例说明优先级高的线程在资源抢夺中更有优势。
 * */
package test;

public class PriorityDemo {
    public static class HightPriority extends Thread{
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (PriorityDemo.class){
                    count++;
                    if (count>10000000){
                        System.out.println("HightPriority id complete");
                        break;
                    }
                }
            }
        }
    }

    public static class LowPriority extends Thread{
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (PriorityDemo.class){
                    count++;
                    if (count>10000000){
                        System.out.println("LowPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread high = new HightPriority();
        LowPriority low = new LowPriority();
        high.setPriority(Thread.MAX_PRIORITY);
        low.setPriority(Thread.MIN_PRIORITY);
        low.start();
        high.start();
    }
}
