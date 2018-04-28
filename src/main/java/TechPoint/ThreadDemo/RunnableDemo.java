package TechPoint.ThreadDemo;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class RunnableDemo implements Runnable{
    private static Lock lock = new ReentrantLock();

    private String name;
    public RunnableDemo(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        lock.lock();
        for (int i = 0; i < 5000; i++) {
            System.out.println(name + "运行  :  " + i);
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lock.unlock();

    }


    public static void main(String[] args) {
        new Thread(new RunnableDemo("Thread_01")).start();
        new Thread(new RunnableDemo("Thread_02")).start();
    }


}