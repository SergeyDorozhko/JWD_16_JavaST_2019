package by.dorozhko.task2demo.main.ex05a_isactive;

public class ThreadToInterupt extends Thread{

    public ThreadToInterupt(String name) {
        super(name);
    }


    public void run() {

        System.out.printf("Поток %s начал работу... \n", Thread.currentThread().getName());
        int counter=1; // счетчик циклов
        while(!Thread.interrupted()){
            System.out.println("поток работает " + counter++);
            try{
                Thread.sleep(1);
            }
            catch(InterruptedException e){
                System.out.println("Поток прерван");
                Thread.currentThread().interrupt();
            }
        }
        System.out.printf("Поток %s завершил работу... \n", Thread.currentThread().getName());

    }
}
