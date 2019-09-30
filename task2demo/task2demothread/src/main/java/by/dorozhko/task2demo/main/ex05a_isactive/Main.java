package by.dorozhko.task2demo.main.ex05a_isactive;

public class Main {
    public static void main(String[] args) {
        System.out.println("Главный поток начал работу...");
        ThreadToDisable myThread = new ThreadToDisable();
        Thread myT = new Thread(myThread, "name of ThreadToDisable");
        myT.start();

        ThreadToInterupt myThreadInter = new ThreadToInterupt("interapt thread");

myThreadInter.start();

        try{
            Thread.sleep(1100);

            myThread.disable();
           myThreadInter.interrupt();
           myThreadInter.interrupt();

            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            System.out.println("Поток прерван");
        }
        System.out.println("Главный поток завершил работу...");
        System.out.println("!!!!!!!!!!!!!!!--------------" + myThreadInter.isInterrupted());
 //       while (myThreadInter.isInterrupted()) {
//            System.out.println("here");
  //          myThreadInter.interrupt();
   //     }
    }
}
