package by.dorozhko.poputka.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Journey extends Entity {
    private User driver;
    private Address startAddress;
    private Address destinationAddress;
    private String departureTime; //hh:mm
    private String date; //dd-mm-yyyy
    private double cost;
    private String currency;
    private int passengersNumber;
    private List<User> passengers;
    private boolean canTakeBaggage;
    private String additionalInformation;

    public Journey() {
        passengers = new ArrayList<>();
    }

    public Journey(final int id, User driver, Address startAddress, Address destinationAddress, String departureTime, String date, double cost, String currency, int numberOfPassengers, boolean canTakeBaggage, String additionalInformation) {
        super(id);
        this.driver = driver;
        this.startAddress = startAddress;
        this.destinationAddress = destinationAddress;
        this.departureTime = departureTime;
        this.date = date;
        this.cost = cost;
        this.currency = currency;
        this.passengersNumber = numberOfPassengers;
        passengers = new ArrayList<>();
        this.canTakeBaggage = canTakeBaggage;
        this.additionalInformation = additionalInformation;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public Address getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(Address startAddress) {
        this.startAddress = startAddress;
    }

    public Address getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(Address destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getPassengersNumber() {
        return passengersNumber;
    }

    public void setPassengersNumber(int passengersNumber) {
        this.passengersNumber = passengersNumber;
    }

    public List<User> getPassengers() {

        return new ArrayList<>(passengers);
    }

    public void addPassenger(User passenger) {
        if (passengers.size() < passengersNumber) {
            passengers.add(passenger);
        }
    }

    public boolean isCanTakeBaggage() {
        return canTakeBaggage;
    }

    public void setCanTakeBaggage(boolean canTakeBaggage) {
        this.canTakeBaggage = canTakeBaggage;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) {
            return false;
        }
        Journey journey = (Journey) o;
        return Double.compare(journey.cost, cost) == 0 &&
                passengersNumber == journey.passengersNumber &&
                canTakeBaggage == journey.canTakeBaggage &&
                Objects.equals(driver, journey.driver) &&
                Objects.equals(startAddress, journey.startAddress) &&
                Objects.equals(destinationAddress, journey.destinationAddress) &&
                Objects.equals(departureTime, journey.departureTime) &&
                Objects.equals(date, journey.date) &&
                Objects.equals(currency, journey.currency) &&
                Objects.equals(passengers, journey.passengers) &&
                Objects.equals(additionalInformation, journey.additionalInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), driver, startAddress, destinationAddress, departureTime, date, cost, currency, passengersNumber, passengers, canTakeBaggage, additionalInformation);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{"
                + "id=" + getId()
                + "driver=" + driver +
                ", startAddress=" + startAddress +
                ", destinationAddress=" + destinationAddress +
                ", departureTime='" + departureTime + '\'' +
                ", date='" + date + '\'' +
                ", cost=" + cost +
                ", currency='" + currency + '\'' +
                ", passengersNumber=" + passengersNumber +
                ", passengers=" + passengers +
                ", canTakeBaggage=" + canTakeBaggage +
                ", additionalInformation='" + additionalInformation + '\'' +
                '}';
    }
}
