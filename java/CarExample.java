public class CarExample {
    public static void main(String[] args) {
        Car firstCar = new Car("Toyota", "Corolla", 2021);
        Car secondCar = new Car("Honda", "Civic", 2024);

        firstCar.displayDetails();
        secondCar.displayDetails();
    }

    static class Car {
        private final String make;
        private final String model;
        private final int year;

        Car(String make, String model, int year) {
            this.make = make;
            this.model = model;
            this.year = year;
        }

        void displayDetails() {
            System.out.println(make + " " + model + " (" + year + ")");
        }
    }
}
