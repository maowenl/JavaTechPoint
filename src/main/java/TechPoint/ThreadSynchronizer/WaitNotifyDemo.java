package TechPoint.ThreadSynchronizer;
import java.util.ArrayList;
import java.util.List;

class Message {
}

class Consumer extends Thread {

    private Producer producer;

    public Consumer(String name, Producer producer) {
        super(name);
        this.producer = producer;
    }

    @Override public void run() {
        while (true) {
            Message msg = producer.waitMsg();
            System.out.println("Consumer " + getName() + " get a msg");
        }
    }

}

class Producer extends Thread {
    List<Message> msgList = new ArrayList<>();

    @Override public void run() {
        try {
            while (true) {
                Thread.sleep(10000);
                Message msg = new Message();
                synchronized(msgList) {
                    msgList.add(msg);
                    System.out.println("\n\n\nProduce a msg.............");
                    msgList.notifyAll(); //这里只能是notify而不能是notifyAll，否则remove(0)会报java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Message waitMsg() {
        synchronized(msgList) {
            if(msgList.size() == 0) {
                try {
                    System.out.println("before wait::thread Name =" + Thread.currentThread().getName());
                    msgList.wait();
                } catch(InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " +++++ " + e.getMessage());
//                    e.printStackTrace();
                }
            }
            System.out.println("Thread Name =" + Thread.currentThread().getName() + "\n");
            return msgList.remove(0);
        }
    }
}


public class WaitNotifyDemo {

    public static void main(String[] args) {
        Producer p = new Producer();
        p.start();
        new Consumer("Consumer1", p).start();
        new Consumer("Consumer2", p).start();
        new Consumer("Consumer3", p).start();
    }
}
