/*
* Create :2019-11-7
* author :Aowen_Tan
* main :测试interrupt(),isInterrupted(),interrupted()三个方法
* */
package test;

public class TestInterrupt {


    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interruted!");
                        break;
                    }
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        System.out.println("interruted When Sleep");
                        //设置中断状态
                        Thread.currentThread().interrupt();
                    }
                    Thread.yield();
                }
            }
        };
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }
}
