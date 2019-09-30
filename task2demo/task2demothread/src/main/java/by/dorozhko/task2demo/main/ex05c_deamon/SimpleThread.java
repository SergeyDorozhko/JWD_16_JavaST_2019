package by.dorozhko.task2demo.main.ex05c_deamon;


public class SimpleThread extends Thread {

    public void run() {
       try {
           if (isDaemon()) {
               System.out.println("start demon");
               Thread.sleep(1000);
           } else {
               System.out.println("start simple");
           }
       }catch (InterruptedException e) {
           System.out.println(e);
       } finally {
           if(!isDaemon()) {
               System.out.println("ne demon");
           } else {
               System.out.println("stop demon");
           }
       }

    }
}
