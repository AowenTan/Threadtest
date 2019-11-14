/*
 * Create :2019-11-14
 * author :Aowen_Tan
 * main :测试拒绝策略中的自定义拒绝策略
 * */
package test.ThreadPool;

import java.util.concurrent.*;

public class RejectThreadPoolDemo {
    public static class MyTask implements Runnable{
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread ID: " + Thread.currentThread().getId());
            try {
               Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        MyTask mt = new MyTask();
        ExecutorService es = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r.toString()+" is discard");
                    }
                });
        for (int i=0;i<Integer.MAX_VALUE;i++){
            es.submit(mt);
            Thread.sleep(10);
        }
    }
}
