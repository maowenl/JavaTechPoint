package TechPoint.ThreadDemo;




public class ThreadDemo extends Thread {

    private String name;

    public ThreadDemo(String name) {
        this.name=name;
    }
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行  :  " + i);
            try {
                sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadDemo mTh1=new ThreadDemo("mTh1");
        ThreadDemo mTh2=new ThreadDemo("mTh2");
        mTh1.start();
        mTh2.start();

        try {
            mTh1.join();
            mTh2.join();
        }catch (Exception e){}

    }

}
