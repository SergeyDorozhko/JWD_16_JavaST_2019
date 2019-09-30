package by.dorozhko.task2demo.main.ex02_person;

public class PersoneRunnable extends Person implements Runnable {

    public PersoneRunnable(String name) {
        super(name);
    }

    public void run(){
        PersoneRunnable den = new RunnblePersonFOrDeamon(getName() + "den" + Thread.currentThread().getName());

        Thread dent = new Thread(den);

        dent.setDaemon(true);

        dent.start();
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + " hello world " + Thread.currentThread().getName() );
        }

    }
}
