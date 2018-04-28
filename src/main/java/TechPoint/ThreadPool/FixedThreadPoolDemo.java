package TechPoint.ThreadPool;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.*;

public class FixedThreadPoolDemo {
    public static void delay(int sec){

        try {
            Thread.sleep(sec * 1000);
        }catch (InterruptedException e){

        }
    }
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;// = new ScheduledThreadPoolExecutor();
        ArrayBlockingQueue abq;
        SynchronousQueue  sq;


        // 创建线程
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        Thread t3 = new MyThread();
        Thread t4 = new MyThread();
        Thread t5 = new MyThread();
        // 将线程放入池中进行执行
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);

        delay(5);
        t1.interrupt();
        System.out.println( "t1.interrupt ............");

        delay(10);
        // 关闭线程池
        pool.shutdown();
        System.out.println( "pool.shutdown() ............");
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        while(true) {
            System.out.println(Thread.currentThread().getName() + "正在执行。。。");
            FixedThreadPoolDemo.delay(3);
        }
    }
}