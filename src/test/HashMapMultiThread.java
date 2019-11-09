/*
 * Create :2019-11-8
 * author :Aowen_Tan
 * main :测试HashMap在多线程中存在的问题
 * 下面的代码跑起来有三种可能
 * 1、程序正常结束，结果为100000
 * 2、程序正常结束，结果不为100000，这是由于多线程的冲突导致的
 * 3、程序无法结束，CPU使用率飙升，这是死锁情况
 * 解决办法：使用ConcurrentHashMap代替HashMap。
 *
 * */
package test;

import java.util.HashMap;
import java.util.Map;

public class HashMapMultiThread {
    static Map<String, String> map = new HashMap<String, String>();
//    static Map<String, String> map = new ConcurrentHashMap<String, String>();

    public static class AddThread implements Runnable{
        int start = 0;
        @Override
        public void run() {
            for (int i = start;i < 100000;i++){
                map.put(Integer.toString(i), Integer.toBinaryString(i));
            }
        }

        public AddThread(int start){
            this.start = start;
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(new HashMapMultiThread.AddThread(0));
        Thread t2 = new Thread(new HashMapMultiThread.AddThread(1));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(map.size());
    }
}
