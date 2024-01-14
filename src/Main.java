
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













public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}