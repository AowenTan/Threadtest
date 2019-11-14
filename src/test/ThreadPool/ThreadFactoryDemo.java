/*
 * Create :2019-11-14
 * author :Aowen_Tan
 * main :测试ThreadFactory 自定义线程池，并且全部设置为守护线程，完成后马上全部强制销毁。
 * */
package test.ThreadPool;

import java.util.concurrent.*;

public class ThreadFactoryDemo {
    public static void main(String[] args) throws InterruptedException{
        RejectThreadPoolDemo.MyTask task = new RejectThreadPoolDemo.MyTask();
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setDaemon(true);
                        System.out.println("create " + t);
                        return t;
                    }
                });
        for (int i=0;i<5;i++){
            es.submit(task);
        }
        Thread.sleep(2000);
    }
}
