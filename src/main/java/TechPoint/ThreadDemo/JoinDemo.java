package TechPoint.ThreadDemo;

public class JoinDemo  extends Thread{
    private String name;
    public JoinDemo(String name) {
        super(name);
        this.name=name;
    }
    public void run()  {
        System.out.println(Thread.currentThread().getName() + " 线程运行开始!");
        for (int i = 0; i < 15; i++) {
            System.out.printf( "%s : %d, activeCount = %d\n",name, i, Thread.activeCount());

            try {
                sleep( 1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        System.out.println(Thread.currentThread().getName() + " 线程运行结束!");
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"主线程运行开始!");
        JoinDemo mTh1=new JoinDemo("mTh1");
        JoinDemo mTh2=new JoinDemo("mTh2");


        System.out.print(Thread.currentThread().getThreadGroup() + "\n"
                + mTh1.getThreadGroup()+ "\n"
                + mTh2.getThreadGroup()+ "\n" + "\n");

        mTh1.start();
        mTh2.start();
        try{
            Thread.sleep( 3000);
        }catch (Exception e){}

        mTh1.interrupt();
        System.out.println("mTh1.isAlive = " + mTh1.isAlive());
        int cnt = Thread.activeCount();
        System.out.printf("activeCount = %d", cnt);
        System.out.println(Thread.currentThread().getName()+ "主线程运行结束!\n");

    }

}