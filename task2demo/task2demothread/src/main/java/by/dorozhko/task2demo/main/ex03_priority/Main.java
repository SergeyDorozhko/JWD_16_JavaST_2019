package by.dorozhko.task2demo.main.ex03_priority;


public class Main {
    public static void main(String[] args) {

        System.out.println("START");
        PriorThread min = new PriorThread("Min");
        PriorThread max = new PriorThread("Max");
        PriorThread norm = new PriorThread("Norm");


        min.setPriority(Thread.MIN_PRIORITY); // 1
        max.setPriority(Thread.MAX_PRIORITY); // 10
        norm.setPriority(Thread.NORM_PRIORITY); // 5
        min.start();
        norm.start();
        max.start();


        try {
            max.join();

            norm.join(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("STOPED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");



    }
}
