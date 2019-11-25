package test;

public class Main {
    private static void printTest(Car car, Checker check){
        if(check.test(car)){
            System.out.println(car);
        }else{
            System.out.println("No");
        }
    }

    public static void main(String[] args) {
        Car audiA3 = new Car("AudiA3", 5000, true, true);
        Car audiA6 = new Car("AudiA6", 8000, false, false);


        Checker checker = new Checker() {
            @Override
            public boolean test(Car car) {
                return car.isFullDrive();
            }
        };

        printTest(audiA3, checker);
        printTest(audiA6, checker);

        printTest(audiA3, new Checker() {
            @Override
            public boolean test(Car car) {
                return car.isGasEngine();
            }
        });

        printTest(audiA3, c -> c.isGasEngine());
        printTest(audiA6, c -> c.isGasEngine());

        printTest(audiA3, Car::isGasEngine);
    }


}
