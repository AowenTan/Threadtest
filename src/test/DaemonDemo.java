/*
 * Create :2019-11-7
 * author :Aowen_Tan
 * main :测试Daemon 守护线程
 * 守护线程默默完成系统性的服务，当用户线程结束，也就是无事可做，守护线程也就不存在，整个应用程序自然结束
 * 当java应用内只剩下守护线程，java虚拟机就会自然退出。
 * */
package test;

public class DaemonDemo {
    public static class DaemonT extends Thread{
        @Override
        public void run() {
            while (true){
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread t = new DaemonT();
        //设置守护线程状态必须在线程开启前设置，否则无效
        t.setDaemon(true);
        t.start();
        Thread.sleep(10000);
    }
}
