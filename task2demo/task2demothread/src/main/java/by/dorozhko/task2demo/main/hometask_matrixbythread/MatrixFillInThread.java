package by.dorozhko.task2demo.main.hometask_matrixbythread;

public class MatrixFillInThread extends Thread {

    private int id;
    private static int idGenerator = 0;

    public MatrixFillInThread(String name){
        super(name);
        id = idGenerator++;
    }

    MatrixStorage matrix = MatrixStorage.getInstance();

    public void run() {

        System.out.println("поток" + currentThread().getName() + " начал работу");
        for(int j = id; j < matrix.getMatrixSize(); j = j + idGenerator) {
            System.out.println("поток" + currentThread().getName() + " начал заполнение новой строки");

            for (int i = 0; i < matrix.getMatrixSize(); i++) {
                matrix.setMatrixIndex(j, i, id);
            }
        }
        System.out.println("поток" + currentThread().getName() + " завершил работу");

    }
}
