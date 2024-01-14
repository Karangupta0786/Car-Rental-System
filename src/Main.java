import java.util.ArrayList;
import java.util.Scanner;

class Car{
    private String carId;
    private String brand;
    private String model;
    private double pricePerDay;
    private boolean isAvailable;

    public String getCarId() {
        return carId;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }
    public boolean getIsAvailable(){
        return isAvailable;
    }
    public double calculatePrice(int rentalDays){
        return pricePerDay * rentalDays;
    }
    public void rent(){
        isAvailable = false;
    }
    public void returnCar(){
        isAvailable = true;
    }
    public Car(String carId,String brand, String model, double pricePerDay){
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.isAvailable = true;
    }
}
class Customer{
    private String customerId;
    private String customerName;

    public String getCustomerId() {
        return customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public Customer(String customerName, String customerId){
        this.customerId = customerId;
        this.customerName = customerName;
    }
}
class Rental{
    private Car car;
    private Customer customer;
    private int days;
    public Rental(Car car, Customer customer, int days){
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() {
        return car;
    }
    public Customer getCustomer() {
        return customer;
    }
    public int getDays() {
        return days;
    }
}
class CarRentalSystem{
    ArrayList<Car> cars;
    ArrayList<Customer> customers;
    ArrayList<Rental> rentals;

    public CarRentalSystem(){
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }
    public void addCars(Car car){
        cars.add(car);
    }
    public void addCustomers(Customer customer){
        customers.add(customer);
    }
    public void rentCar(Car car, Customer customer, int days){
        if (car.getIsAvailable()){
            car.rent();
            rentals.add(new Rental(car,customer,days));
        }
        else {
            System.out.println("this Car is not available for rent");
        }
    }
    public void returnCar(Car car){
        if (!car.getIsAvailable()){
            car.returnCar();
        }
        Rental rentalToRemove = null;
        for (Rental rental : rentals){
            if (rental.getCar() == car){
                rentalToRemove = rental;
                break;
            }
            if (rentalToRemove.getCar()!=null){
                rentals.remove(rentalToRemove);
//                System.out.println("car returned successfully.");
            }
            else {
                System.out.println("car was not rented");
            }
        }
    }
    public void menu(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("==== Car Rental System ====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            if (choice == 1){
                System.out.println("\n == Rent a Car ==\n");
                System.out.println("Enter your name");
                String customerName = scanner.next();
                System.out.println("\nAvailable Cars: ");
                for (Car car: cars){
                    if (car.getIsAvailable()){
                        System.out.println(car.getCarId()+" - "+car.getBrand()+" "+car.getModel());
                    }
                }
                System.out.println("Enter the Car Id you want to rent: ");
                String carId = scanner.next();

                System.out.println("Enter the number of days for rental:");
                int rentalDays = scanner.nextInt();

                Customer newCustomer = new Customer(customerName,"cus"+customers.size()+1);
                addCustomers(newCustomer);

                Car selectedCar = null;
                for (Car car : cars){
                    if (car.getCarId().equals(carId) && car.getIsAvailable()){
                        selectedCar = car;
                        break;
                    }
                }
                if (selectedCar != null){
                    double totalPrice = selectedCar.calculatePrice(rentalDays);
                    System.out.println("\n ==Rental information== \n");
                    System.out.println("Customer Id: "+newCustomer.getCustomerId());
                    System.out.println("Customer name: "+newCustomer.getCustomerName());
                    System.out.println("Car: "+selectedCar.getBrand()+" "+selectedCar.getModel());
                    System.out.printf("Total price: $%.2f%n",totalPrice);

                    System.out.println("\n Confirm rental (Y/N)\n");
                    String confirmation = scanner.next();
                    if (confirmation.equalsIgnoreCase("Y")){
                        rentCar(selectedCar,newCustomer,rentalDays);
                        System.out.println("\nCar rented successfully");
                    }
                    else {
                        System.out.println("\n rental cancelled.");
                    }
                }
                else {
                    System.out.println("Invalid car selection or car is not available for rent\n");
                }
            }
            else if (choice == 2) {
                System.out.println("\n == Return a Car ==");
                System.out.println("\nEnter the Car Id you want to return:");
                String carId = scanner.next();
                scanner.nextLine();

                Car returnableCar = null;

                for (Car car : cars){
                    if (car.getCarId().equals(carId) && !car.getIsAvailable()){
                        returnableCar = car;
                        break;
                    }
                }

                if (returnableCar!=null){
                    Customer customer = null;
                    for (Rental rental :rentals){
                        if (rental.getCar().equals(returnableCar)){
                            customer = rental.getCustomer();
                            break;
                        }
                    }
                    if (customer!=null){
                        System.out.println("Car successfully returned by "+customer.getCustomerName());
                        returnCar(returnableCar);
                    }
                    else {
                        System.out.println("Car was not rented or rental information is missing");
                    }
                }
                else {
                    System.out.println("Invalid car or car is not rented");
                }
            } else if (choice == 3) {
                break;
            }
            else {
                System.out.println("Please choose a valid option..");
            }
        }
        System.out.println("\n Thank you for using car rental system!");
    }
}

public class Main {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();
        Car car1 = new Car("c1","Mahindra","Thar",1000);
        Car car2 = new Car("c2","Mahindra","Scorpio",1200);
        Car car3 = new Car("c3","Tata","Safari",1100);
        Car car4 = new Car("c4","Toyota","Fortuner",1600);
        rentalSystem.cars.add(car1);
        rentalSystem.cars.add(car2);
        rentalSystem.addCars(car3);
        rentalSystem.addCars(car4);
        rentalSystem.menu();
    }
}