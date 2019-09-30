package by.dorozhko.task2demo.main.ex02_person;

public class Main {
    public static void main(String[] args){
        PersoneRunnable al = new PersoneRunnable("alice");
    Thread alt = new Thread(al);

        PersoneRunnable smit = new PersoneRunnable("smit");

    Thread smitThread = new Thread(smit);

    alt.setPriority(1);
    smitThread.setPriority(3);

    alt.start();

    smitThread.start();


    }
}
