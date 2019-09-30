package by.dorozhko.task2demo.main.ex06_commonresourse;

public class Main {
    public static void main(String[] args) {
        CommonResourse commonResource= new CommonResourse();
        for (int i = 1; i < 6; i++){

            Thread t = new Thread(new CountThread(commonResource));
            t.setName("Поток "+ i);
            t.start();
        }

    }
}
