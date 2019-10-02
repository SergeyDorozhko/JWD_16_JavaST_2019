package by.dorozhko.task2demo.main.ex08_threadsecuritystringbuffer;

public class TwoThread {
    public static int counter = 0;
    static StringBuffer s = new StringBuffer();


    public static void main(String args[]) {
        new Thread() {
            public void run() {
                synchronized (s) {
                    do {
                        s.append("A");
                        System.out.print("> " + counter + " ");
                        System.out.println(s);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            System.err.print(e);
                        }
                    } while (TwoThread.counter++ < 2);
                } // конец synchronized
            }
        }.start();
        new Thread() {
            public void run() {
                synchronized (s) {
                    while (TwoThread.counter++ < 6) {
                        s.append("B");
                        System.out.print("< " + counter + " ");
                        System.out.println(s);
                    }
                } // конец synchronized
            }

        }.start();
    }
}
