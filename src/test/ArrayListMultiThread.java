/*
* Create :2019-11-8
* author :Aowen_Tan
* main :测试ArrayList在多线程中存在的问题
* 下面的代码跑起来有三种可能
* 1、程序正常结束，结果为200000，说明即使并行程序有问题，也未必每次都会表现出来
* 2、程序正常结束，结果小于200000，这是由于多线程的冲突导致的
* 3、程序抛出异常，ArrayIndexOutOfBoundsException  原因是ArrayList在扩容过程中内部一致性被破坏，此时另一个线程访问了不一致的内部状态，所以出现越界问题。
* 解决办法:使用Vector代替ArrayList
* */
package test;

import java.util.ArrayList;

public class ArrayListMultiThread {
    static ArrayList<Integer> al = new ArrayList<Integer>(10);
    public static class AddThread implements Runnable{
        @Override
        public void run() {
            for (int i=0;i<100000;i++){
                al.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(new AddThread());
        Thread t2 = new Thread(new AddThread());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(al.size());
    }
}
