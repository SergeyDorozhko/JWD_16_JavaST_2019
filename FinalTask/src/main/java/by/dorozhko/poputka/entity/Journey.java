package by.dorozhko.poputka.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Journey extends Entity implements Serializable {
    private User driver;
    private Address startAddress;
    private Address destinationAddress;
    private LocalTime departureTime; //hh:mm
    private LocalDate departureDate; //yyyy-mm-dd
    private double cost;
    private String currency;
    private int passengersNumber;
    private List<User> passengers;
    private String additionalInformation;

    public Journey() {
        passengers = new ArrayList<>();
    }

    public Journey(User driver, Address startAddress, Address destinationAddress, String departureTime, String date, double cost, String currency, int numberOfPassengers, String additionalInformation) {
        this.driver = driver;
        this.startAddress = startAddress;
        this.destinationAddress = destinationAddress;
        this.departureTime = LocalTime.parse(departureTime);
        this.departureDate = LocalDate.parse(date);
        this.cost = cost;
        this.currency = currency;
        this.passengersNumber = numberOfPassengers;
        passengers = new ArrayList<>();
        this.additionalInformation = additionalInformation;
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

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(final String time) {
        departureTime = LocalTime.parse(time);
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(final String date) {
        this.departureDate = LocalDate.parse(date);
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
                Objects.equals(driver, journey.driver) &&
                Objects.equals(startAddress, journey.startAddress) &&
                Objects.equals(destinationAddress, journey.destinationAddress) &&
                Objects.equals(departureTime, journey.departureTime) &&
                Objects.equals(departureDate, journey.departureDate) &&
                Objects.equals(currency, journey.currency) &&
                Objects.equals(passengers, journey.passengers) &&
                Objects.equals(additionalInformation, journey.additionalInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), driver, startAddress, destinationAddress, departureTime, departureDate, cost, currency, passengersNumber, passengers, additionalInformation);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{"
                + "id=" + getId()
                + "driver=" + driver +
                ", startAddress=" + startAddress +
                ", destinationAddress=" + destinationAddress +
                ", departureTime='" + departureTime + '\'' +
                ", date='" + departureDate + '\'' +
                ", cost=" + cost +
                ", currency='" + currency + '\'' +
                ", passengersNumber=" + passengersNumber +
                ", passengers=" + passengers +
                ", additionalInformation='" + additionalInformation + '\'' +
                '}';
    }
}
