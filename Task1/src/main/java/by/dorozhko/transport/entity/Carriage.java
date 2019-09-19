package by.dorozhko.transport.entity;


import by.dorozhko.transport.entity.params.CarriageType;


public class Carriage extends TransportEntity {
    /**
     * Carriage type (set by enum).
     */
    private CarriageType type;

    /**
     * Max value of passengers.
     */
    private int numberOfPassengers;


    /**
     * max available baggage (number of positions).
     */
    private int maxValueOfBaggage;


    /**
     * Constructor of Carriage.
     *
     * @param name               super class.
     * @param weightInKilo       superclass.
     * @param lengthInMeters     superclass.
     * @param type               Type of carriage (set  by enum).
     * @param numberOfPassengers max value of passengers in Carriage.
     * @param maxValueOfBaggage  max value of baggage (in positions) in Carriage.
     */
    public Carriage(String name, int weightInKilo, int lengthInMeters, CarriageType type, int numberOfPassengers, int maxValueOfBaggage) {
        super(name, weightInKilo, lengthInMeters);
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
        this.maxValueOfBaggage = maxValueOfBaggage;
    }


    /**
     * @return type of carriage.
     */
    public CarriageType getType() {
        return type;
    }

    /**
     * This method set type of carriage..
     *
     * @param type
     */
    public void setType(CarriageType type) {
        this.type = type;
    }

    /**
     * This method get numbers of passengers of carriage.
     *
     * @return Number of passengers.
     */
    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    /**
     * This method set available number of passengers in carriage.
     *
     * @param numberOfPassengers
     */
    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    /**
     * This method get max valu of baggage in carriage.
     *
     * @return max value of bagage.
     */
    public int getMaxValueOfBaggage() {
        return maxValueOfBaggage;
    }

    /**
     * This method set max available value of baggage in carriage.
     *
     * @param maxValueOfBaggage
     */
    public void setMaxValueOfBaggage(int maxValueOfBaggage) {
        this.maxValueOfBaggage = maxValueOfBaggage;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = result * prime + type.hashCode();
        result = result * prime + numberOfPassengers;
        result = result * prime + maxValueOfBaggage;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Carriage obj = (Carriage) o;

        if (this.type.equals(obj.type)) {
            return false;
        }
        if (this.maxValueOfBaggage != obj.maxValueOfBaggage) {
            return false;
        }
        if (this.numberOfPassengers != obj.numberOfPassengers) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": id = " + super.getId() + ", name = " + super.getName()
                + ", weight = " + super.getWeightInKilo() + ", length = " + super.getLengthInMeters()
                + ", carriageType = " + type + ", maxValueOfBaggage = " + maxValueOfBaggage
                + ", numberOfPassengers = " + numberOfPassengers;
    }
}